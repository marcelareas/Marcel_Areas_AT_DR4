package br.edu.infnet.marcel.empresa.repository;

import br.edu.infnet.marcel.empresa.domain.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, Integer> {

    List<Vaga> findByIdUsuario(int id);

    List<Vaga> findByCargoContainingIgnoreCase(String consulta);

    List<Vaga> findByCidadeContainingIgnoreCase(String consulta);

}
