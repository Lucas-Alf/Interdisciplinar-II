package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario WHERE email = ?1 and senha = ?2 LIMIT 1", nativeQuery = true)
    Usuario login(String email, String senha);

    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    List<Usuario> lista();
}
