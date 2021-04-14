package br.edu.infnet.marcelrh.service;

import br.edu.infnet.marcelrh.domain.Resposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8091/respostas", name = "Respostas")
public interface RespostaService {

    @GetMapping(path = "/usuario/{idUsuario}")
    List<Resposta> listarPorIdUsuario(@PathVariable int idUsuario);

    @PostMapping
    Resposta responderVaga(@RequestBody Resposta resposta);

}
