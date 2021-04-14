package br.edu.infnet.marcelrh;

import br.edu.infnet.marcelrh.domain.Criterio;
import br.edu.infnet.marcelrh.domain.Resposta;
import br.edu.infnet.marcelrh.domain.RespostaCriterio;
import br.edu.infnet.marcelrh.domain.Usuario;
import br.edu.infnet.marcelrh.domain.Vaga;
import br.edu.infnet.marcelrh.service.RespostaService;
import br.edu.infnet.marcelrh.service.UsuarioService;
import br.edu.infnet.marcelrh.service.VagaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MarcelRhApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(MarcelRhApplication.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VagaService vagaService;

	@Autowired
	private RespostaService respostaService;

	public static void main(String[] args) {
		SpringApplication.run(MarcelRhApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		criarUsuarioEmpresa();

		cadastroUsuarioCandidato();

		testarLoginUsuarioEmpresa();

		testarLoginUsuarioCandidato();

		cadastrarEPublicarPrimeiraVaga();

		cadastrarEPublicarSegundaVaga();

		buscarVagaProgramador();

		buscarVagaAnalista();

		buscarVagaRJ();

		buscarVagaMG();

		responderVagaProgramador();

		responderVagaAnalista();

	}

	private void responderVagaProgramador() {
		log.info("Responder Vaga de Programador");
		List<Vaga> lista = vagaService.listarPorExemploCargo("programador");
		Vaga vaga = lista.get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Usuario usuario = usuarioService.obterPorEmail("candidato@teste.com");
		Resposta resposta = new Resposta();
		resposta.setData(new Date());
		resposta.setIdUsuario(usuario.getId());
		resposta.setIdVaga(vaga.getId());

		List<Criterio> criterios = vaga.getCriterioList();
		ArrayList<RespostaCriterio> listaRespostaCriterio = new ArrayList<>();
		double indice = 0;
		int contador = 0;

		for (Criterio criterio : criterios) {
			RespostaCriterio rc = new RespostaCriterio();
			rc.setIdCriterio(criterio.getId());
			rc.setIdResposta(resposta);
			rc.setResposta(5);

			listaRespostaCriterio.add(rc);

			indice += 5 * criterio.getPeso();
			contador++;
		}

		indice = indice / contador;
		resposta.setIndice(indice);
		resposta.setRespostaCriterioList(listaRespostaCriterio);
		Resposta gravado = respostaService.responderVaga(resposta);


		log.info("Vaga Respondida em " + formatter.format(gravado.getData()) + " por " + usuario.getNome());
	}

	private void responderVagaAnalista() {
		log.info("Responder Vaga de Analista");
		List<Vaga> lista = vagaService.listarPorExemploCargo("analista");
		Vaga vaga = lista.get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Usuario usuario = usuarioService.obterPorEmail("candidato@teste.com");
		Resposta resposta = new Resposta();
		resposta.setData(new Date());
		resposta.setIdUsuario(usuario.getId());
		resposta.setIdVaga(vaga.getId());

		List<Criterio> criterios = vaga.getCriterioList();
		ArrayList<RespostaCriterio> listaRespostaCriterio = new ArrayList<>();
		double indice = 0;
		int contador = 0;

		for (Criterio criterio : criterios) {
			RespostaCriterio rc = new RespostaCriterio();
			rc.setIdCriterio(criterio.getId());
			rc.setIdResposta(resposta);
			rc.setResposta(5);

			listaRespostaCriterio.add(rc);

			indice += 4 * criterio.getPeso();
			contador++;
		}

		indice = indice / contador;
		resposta.setIndice(indice);
		resposta.setRespostaCriterioList(listaRespostaCriterio);
		Resposta gravado = respostaService.responderVaga(resposta);

		log.info("Vaga Respondida em " + formatter.format(gravado.getData()) + " por " + usuario.getNome());
	}

	private void buscarVagaMG() {
		log.info("Buscando vaga na cidade de MG");
		pesquisarVagaCidade("mg");
	}

	private void buscarVagaRJ() {
		log.info("Buscando vaga na cidade do RJ");
		pesquisarVagaCidade("rj");
	}

	private void buscarVagaAnalista() {
		log.info("Buscando vaga com o termo - analista");
		pesquisarVagaCargo("analista");
	}

	private void buscarVagaProgramador() {
		log.info("Buscando vaga com o termo - programador");
		pesquisarVagaCargo("programador");
	}

	private void testarLoginUsuarioCandidato() {
		// TESTE DE LOGIN COM SUCESSO USUARIO CANDIDATO
		Usuario candidatoLoginSucesso = efetuarLogin("candidato@teste.com", "1234");
		verificarSucessoLogin(candidatoLoginSucesso);

		// TESTE DE LOGIN COM FALHA USUARIO CANDIDATO
		Usuario candidatoLoginFalha = efetuarLogin("candidato@teste.com.br", "12345");
		verificarSucessoLogin(candidatoLoginFalha);
	}

	private void testarLoginUsuarioEmpresa() {
		// TESTE DE LOGIN COM SUCESSO USUARIO EMPRESA
		Usuario empresaLoginSucesso = efetuarLogin("teste@teste.com.br", "1234");
		verificarSucessoLogin(empresaLoginSucesso);

		// TESTE DE LOGIN COM FALHA USUARIO EMPRESA
		Usuario empresaLoginFalha = efetuarLogin("teste@teste.com", "12345");
		verificarSucessoLogin(empresaLoginFalha);
	}

	private void cadastrarEPublicarSegundaVaga() {
		Vaga vaga2 = new Vaga();
		Usuario usuario = usuarioService.obterPorEmail("teste@teste.com.br");
		vaga2.setCargo("ANALISTA DE TESTE");
		vaga2.setCidade("MG");
		vaga2.setFormaContratacao("PJ");
		vaga2.setIdUsuario(usuario.getId());

		Criterio c1 = new Criterio();
		c1.setDescricao("EXPERIÊNCIA COM TESTES AUTOMATIZADOS");
		c1.setIdVaga(vaga2);
		c1.setPerfil(1);
		c1.setPeso(1);

		Criterio c2 = new Criterio();
		c2.setDescricao("CONHECIMENTO SELENIUM");
		c2.setIdVaga(vaga2);
		c2.setPerfil(2);
		c2.setPeso(2);


		ArrayList<Criterio> criterios = new ArrayList<>();
		criterios.add(c1);
		criterios.add(c2);
		vaga2.setCriterioList(criterios);

		Vaga gravado = vagaService.publicarVaga(vaga2);
		log.info("Vaga para " + gravado.getCargo() + " publicado com sucesso");
	}

	private void cadastrarEPublicarPrimeiraVaga() {
		Vaga vaga1 = new Vaga();
		Usuario usuario = usuarioService.obterPorEmail("teste@teste.com.br");
		vaga1.setCargo("PROGRAMADOR JAVA JR");
		vaga1.setCidade("RJ");
		vaga1.setFormaContratacao("CLT");
		vaga1.setIdUsuario(usuario.getId());


		Criterio c1 = new Criterio();
		c1.setDescricao("EXPERIÊNCIA COM JAVA");
		c1.setIdVaga(vaga1);
		c1.setPerfil(1);
		c1.setPeso(2);

		Criterio c2 = new Criterio();
		c2.setDescricao("CONHECIMENTO MYSQL");
		c2.setIdVaga(vaga1);
		c2.setPerfil(2);
		c2.setPeso(1);

		ArrayList<Criterio> criterios = new ArrayList<>();
		criterios.add(c1);
		criterios.add(c2);
		vaga1.setCriterioList(criterios);

		Vaga gravado = vagaService.publicarVaga(vaga1);
		log.info("Vaga para " + gravado.getCargo() + " publicado com sucesso");
	}

	private void cadastroUsuarioCandidato() {
		Usuario usuarioCandidato = new Usuario();

		usuarioCandidato.setNome("NOME CANDIDATO");
		usuarioCandidato.setEndereco("ENDEREÇO CANDIDATO");
		usuarioCandidato.setTelefone("21 0000-9999");
		usuarioCandidato.setEmail("candidato@teste.com");
		usuarioCandidato.setSenha("1234");
		usuarioCandidato.setCpf("28803187006");
		usuarioCandidato.setTipo('C');

		inserirOuAtualizarUsuario(usuarioCandidato);
		log.info("Usuário cadastrado: " + usuarioCandidato.getNome());
	}

	private void criarUsuarioEmpresa() {
		Usuario usuarioEmpresa = new Usuario();

		usuarioEmpresa.setNome("EMPRESA DE TESTE");
		usuarioEmpresa.setEndereco("ENDEREÇO TESTE");
		usuarioEmpresa.setTelefone("21 0000-0000");
		usuarioEmpresa.setEmail("teste@teste.com.br");
		usuarioEmpresa.setSenha("1234");
		usuarioEmpresa.setRazaoSocial("EMPRESA DE TESTE LTDA");
		usuarioEmpresa.setCnpj("68102958000127");
		usuarioEmpresa.setTipo('E');

		inserirOuAtualizarUsuario(usuarioEmpresa);
		log.info("Usuário empresa cadastrado: " + usuarioEmpresa.getNome());
	}

	private Usuario efetuarLogin(String email, String senha) {
		Usuario retorno = null;

		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(senha)) {
			try {
				Usuario usuario = usuarioService.obterPorEmail(email);
				retorno = usuario;
			} catch (Exception e) {
				// LOGIN NÃO EFETUADO
			}
		}
		return retorno;
	}

	private void verificarSucessoLogin(Usuario usuario){
		if (usuario != null) {
			log.info("o Usuario com o id: " + usuario.getId() + " e nome: " + usuario.getNome() + " logou com sucesso");
		} else {
			log.info("Login não efetuado");
		}
	}

	private Usuario inserirOuAtualizarUsuario(Usuario usuario) {
		Usuario retorno  = null;
		try {
			retorno = usuarioService.obterPorEmail(usuario.getEmail());
			if(retorno.getEmail().equals(usuario.getEmail())){
				return retorno;
			}
		} catch (Exception e) {
			// LOGIN NÃO EFETUADO
		}

		if (usuario != null && usuario.getId() == null) {
			retorno = usuarioService.inserirUsuario(usuario);
		} else if (usuario != null && usuario.getId() != null) {
			retorno = usuarioService.atualizarUsuario(usuario);
		}
		return retorno;

	}

	private void pesquisarVagaCargo(String termo){
		List<Vaga> vagas = vagaService.listarPorExemploCargo(termo);
		if(!vagas.isEmpty()) {
			Vaga vaga = vagas.get(0);
			log.info("Vaga encontrada com id: " + vaga.getId() + " e cargo " + vaga.getCargo());
		}else {
			log.info("Nenhuma vaga encontrada com o termo digitado.");
		}
	}

	private void pesquisarVagaCidade(String termo){
		List<Vaga> vagas = vagaService.listarPorExemploCidade(termo);
		if(!vagas.isEmpty()) {
			Vaga vaga = vagas.get(0);
			log.info("Vaga encontrada com id: " + vaga.getId() + " e cargo " + vaga.getCargo());
		}else {
			log.info("Nenhuma vaga encontrada com o cidade digitada.");
		}
	}
}

