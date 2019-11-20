

package br.com.setrem.interdisciplinarII.repository;


import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query("SELECT e FROM pagamento e WHERE e.descricao LIKE %?1%")
    public List<Pagamento> pesquisar(String descricao);
    }
