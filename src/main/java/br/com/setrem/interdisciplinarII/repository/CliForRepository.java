package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.CliFor;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CliForRepository extends JpaRepository<CliFor, String> {
    @Query(value = "SELECT * FROM clifor WHERE tipocliente = 'E'", nativeQuery = true)
    List<CliFor> ListaEmpresa();
    
    @Query(value = "SELECT * FROM clifor WHERE id = ?", nativeQuery = true)
    CliFor BuscaPorId(String id);
}