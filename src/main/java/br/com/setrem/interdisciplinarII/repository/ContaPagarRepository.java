
package br.com.setrem.interdisciplinarII.repository;
import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Integer> {

    @Query("SELECT e FROM contapagar e WHERE e.nome LIKE %?1%")
    public List<ContaPagar> pesquisar(String descricao);
    }

