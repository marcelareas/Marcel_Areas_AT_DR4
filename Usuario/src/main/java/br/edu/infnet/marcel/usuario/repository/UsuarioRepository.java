package br.edu.infnet.marcel.usuario.repository;

import br.edu.infnet.marcel.usuario.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

}
