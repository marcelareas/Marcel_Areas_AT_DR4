package br.edu.infnet.marcel.empresa.controller;


import br.edu.infnet.marcel.empresa.domain.Criterio;
import br.edu.infnet.marcel.empresa.domain.Vaga;
import br.edu.infnet.marcel.empresa.repository.VagaRepository;
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
@RequestMapping(path = {"/vagas"})
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity obterPorId(@PathVariable int id) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        Vaga vaga = null;

        try {
            vaga = vagaRepository.findById(id).get();
        } catch (Exception e) {
            // VAGA PERMANECE NULA
        }

        if (vaga != null) retorno = ResponseEntity.ok().body(vaga);
        return retorno;
    }

    @GetMapping(path = "/lista")
    public ResponseEntity obterLista(){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            List<Vaga> lista = (List<Vaga>)vagaRepository.findAll();

            if (!lista.isEmpty()) retorno = ResponseEntity.ok().body(lista);

        } catch (Exception e) {
        }
        return retorno;
    }

    @GetMapping(path = "/usuario/{idUsuario}")
    public ResponseEntity listarPorIdUsuario(@PathVariable int idUsuario) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            List<Vaga> lista = vagaRepository.findByIdUsuario(idUsuario);

            if (!lista.isEmpty()) retorno = ResponseEntity.ok().body(lista);
        } catch (Exception e) {
        }
        return retorno;
    }

    @GetMapping(path = "/cargo/{exemploCargo}")
    public ResponseEntity listarPorExemploCargo(@PathVariable String exemploCargo) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            List<Vaga> lista = vagaRepository.findByCargoContainingIgnoreCase(exemploCargo);

            if (!lista.isEmpty()) retorno = ResponseEntity.ok().body(lista);
        } catch (Exception e) {
        }
        return retorno;
    }

    @GetMapping(path = "/cidade/{exemploCidade}")
    public ResponseEntity listarPorExemploCidade(@PathVariable String exemploCidade) {
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            List<Vaga> lista = vagaRepository.findByCidadeContainingIgnoreCase(exemploCidade);

            if (!lista.isEmpty()) retorno = ResponseEntity.ok().body(lista);
        } catch (Exception e) {
        }
        return retorno;
    }

    @PostMapping
    public ResponseEntity publicarVaga(@RequestBody Vaga vaga) {
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        List<Criterio> listaCriterio = vaga.getCriterioList();

        if (listaCriterio != null && !listaCriterio.isEmpty()) {
            for (Criterio criterio : listaCriterio) criterio.setIdVaga(vaga);
            Vaga gravado = vagaRepository.save(vaga);
            retorno = ResponseEntity.ok().body(gravado);
        }
        return retorno;
    }
}
