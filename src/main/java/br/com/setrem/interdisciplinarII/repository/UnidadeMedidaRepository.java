
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.UnidadeMedida;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Integer> {

    @Query("SELECT e FROM marca e WHERE e.nome LIKE %?1%")
    public List<UnidadeMedida> pesquisar(String descricao);

}