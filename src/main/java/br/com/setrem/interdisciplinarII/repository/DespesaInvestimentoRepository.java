package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.DespesaInvestimento;

@Repository
public interface DespesaInvestimentoRepository extends JpaRepository<DespesaInvestimento, Integer> {

    @Query(value = "select * from despesainvestimento a inner join patrimonio b on (a.patrimonioid = b.id) inner join produto c on (c.id = b.produtoid) where a.cliforid = ?1", nativeQuery = true)
    public List<DespesaInvestimento> AtualizarTabela(String empresa);

}