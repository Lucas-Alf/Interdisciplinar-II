
package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

    @Query("SELECT e FROM marca e WHERE e.nome LIKE %?1%")
    public List<Local> pesquisar(String descricao);

}