package wear.digital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import wear.digital.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String username);

}
