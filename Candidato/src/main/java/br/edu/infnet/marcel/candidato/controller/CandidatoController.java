package br.edu.infnet.marcel.candidato.controller;


import br.edu.infnet.marcel.candidato.domain.Resposta;
import br.edu.infnet.marcel.candidato.domain.RespostaCriterio;
import br.edu.infnet.marcel.candidato.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/respostas"})
public class CandidatoController {

    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping(path = "/usuario/{idUsuario}")
    public ResponseEntity listarPorIdUsuario(@PathVariable int idUsuario) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            List<Resposta> lista = respostaRepository.findByIdUsuario(idUsuario);
            if (!lista.isEmpty()) retorno = ResponseEntity.ok().body(lista);
        } catch (Exception e) {
        }
        return retorno;
    }

    @PostMapping
    public ResponseEntity responderVaga(@RequestBody Resposta resposta) {
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        List<RespostaCriterio> listaRespostaCriterio = resposta.getRespostaCriterioList();

        if (listaRespostaCriterio != null && !listaRespostaCriterio.isEmpty()) {
            for (RespostaCriterio respostaCriterio : listaRespostaCriterio) respostaCriterio.setIdResposta(resposta);
            Resposta gravado = respostaRepository.save(resposta);
            retorno = ResponseEntity.ok().body(gravado);
        }
        return retorno;
    }
}
