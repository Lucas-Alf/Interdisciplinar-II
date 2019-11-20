

package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Integer> {

    @Query("SELECT e FROM contareceber e WHERE e.descricao LIKE %?1%")
    public List<ContaReceber> pesquisar(String descricao);
    }

