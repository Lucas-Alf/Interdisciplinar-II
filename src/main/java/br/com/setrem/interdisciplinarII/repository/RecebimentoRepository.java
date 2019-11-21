

package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Integer> {

    @Query("SELECT e FROM recebimento e WHERE e.descricao LIKE %?1%")
    public List<Recebimento> pesquisar(String descricao);
    }