
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.UnidadeMedida;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Integer> {
    
    @Query(value = "select * from unidademedida where descricao like %?1% and cliforid = ?2", nativeQuery = true)
    public List<UnidadeMedida> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from unidademedida where cliforid = ?1", nativeQuery = true)
    public List<UnidadeMedida> AtualizarTabela(String empresa);

}