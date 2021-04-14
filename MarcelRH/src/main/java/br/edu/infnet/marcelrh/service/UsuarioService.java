package br.edu.infnet.marcelrh.service;

import br.edu.infnet.marcelrh.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8093/usuarios", name = "Usuarios")
public interface UsuarioService {

    @GetMapping(path = "/{id}")
    Usuario obterPorId(@PathVariable int id);

    @GetMapping(path = {"/email/{email}"})
    Usuario obterPorEmail(@PathVariable String email);

    @PostMapping
    Usuario inserirUsuario(@RequestBody Usuario usuario);

    @PutMapping(path = "/{id}")
    Usuario atualizarUsuario(@RequestBody Usuario usuario);
}
