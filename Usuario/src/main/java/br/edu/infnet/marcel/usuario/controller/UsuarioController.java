package br.edu.infnet.marcel.usuario.controller;


import br.edu.infnet.marcel.usuario.domain.Usuario;
import br.edu.infnet.marcel.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/usuarios"})
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(path = "/{id}")
    public ResponseEntity obterPorId(@PathVariable int id) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Usuario usuario = null;

        try {
            usuario = usuarioRepository.findById(id).get();
        } catch (Exception e) {
            // USUARIO PERMANECE NULO
        }

        if (usuario != null) retorno = ResponseEntity.ok().body(usuario);
        return retorno;
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity obterPorEmail(@PathVariable String email) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);

            if (usuario != null) retorno = ResponseEntity.ok().body(usuario);
        } catch (Exception e) {
            // RETORNO VAZIO
        }
        return retorno;
    }

    @PostMapping
    public ResponseEntity inserirUsuario(@RequestBody Usuario usuario) {
        ResponseEntity retorno = ResponseEntity.badRequest().build();

        if (usuario != null && usuario.getId() == null) {
            Usuario gravado = usuarioRepository.save(usuario);
            retorno = ResponseEntity.ok().body(gravado);
        }
        return retorno;
    }

    @PutMapping
    public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario) {
        ResponseEntity retorno = ResponseEntity.badRequest().build();

        if (usuario != null && usuario.getId() != null) {
            Usuario usuarioPersistido = null;

            try {
                usuarioPersistido = usuarioRepository.findById(usuario.getId()).get();
            } catch (Exception e) {
                // USUARIO NÃO ENCONTRADO NO BANCO
            }

            if (usuarioPersistido != null) {

                try {
                    retorno = ResponseEntity.ok().body(usuarioRepository.save(usuario));
                } catch (Exception e) {
                }
            }
        }
        return retorno;
    }
}
