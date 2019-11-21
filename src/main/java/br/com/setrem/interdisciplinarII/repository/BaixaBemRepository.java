package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.BaixaBem;

@Repository
public interface BaixaBemRepository extends JpaRepository<BaixaBem, Integer> {

    @Query(value = "select * from baixabem a inner join patrimonio b on (a.patrimonioid = b.id) inner join produto c on (b.produtoid = c.id) where a.cliforid = ?1", nativeQuery = true)
    public List<BaixaBem> AtualizarTabela(String empresa);

    @Query(value = "select * from baixabem a inner join patrimonio b on (a.patrimonioid = b.id) inner join produto c on (b.produtoid = c.id) where c.nome like %?1% and a.cliforid = ?2", nativeQuery = true)
    public List<BaixaBem> Pesquisar(String descricao, String empresa);

}