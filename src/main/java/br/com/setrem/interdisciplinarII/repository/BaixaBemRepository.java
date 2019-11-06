package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.BaixaBem;

@Repository
public interface BaixaBemRepository extends JpaRepository<BaixaBem, Integer> {

    @Query("SELECT e FROM baixabem e INNER JOIN e.patrimonioid t inner join t.produtoid i")
    public List<BaixaBem> AtualizarTabela();
    
    // @Query("SELECT e FROM baixabem e INNER JOIN e.patrimonioid t t.produtoid i where i.nome LIKE %?1%")
    // public List<BaixaBem> pesquisar(String descricao);

}