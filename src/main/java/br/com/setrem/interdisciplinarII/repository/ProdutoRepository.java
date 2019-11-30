
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "select * from produto where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Produto> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from produto where cliforid = ?1", nativeQuery = true)
    public List<Produto> AtualizarTabela(String empresa);
    
    @Query(value = "select * from produto a where exists (select * from saldo where produtoid = a.id) and cliforid = ?1", nativeQuery = true)
    public List<Produto> BuscaProdutoPatrimonio(String empresa);

}