package br.edu.infnet.marcel.candidato.repository;

import br.edu.infnet.marcel.candidato.domain.Resposta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RespostaRepository extends CrudRepository<Resposta, Integer> {

    List<Resposta> findByIdUsuario(int idUsuario);

}
