package br.edu.infnet.marcelrh.service;


import br.edu.infnet.marcelrh.domain.Vaga;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(url = "http://localhost:8092/vagas", name = "Vagas")
public interface VagaService {

    @PostMapping
    Vaga publicarVaga(@RequestBody Vaga vaga);

    @GetMapping(path = "/usuario/{idUsuario}")
    List<Vaga> listarPorIdUsuario(@PathVariable int idUsuario);

    @GetMapping(path = "/cargo/{exemploCargo}")
    List<Vaga> listarPorExemploCargo(@PathVariable String exemploCargo);

    @GetMapping(path = "/cidade/{exemploCidade}")
    List<Vaga> listarPorExemploCidade(@PathVariable String exemploCidade);

}
