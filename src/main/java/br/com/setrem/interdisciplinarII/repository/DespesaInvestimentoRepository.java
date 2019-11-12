package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.DespesaInvestimento;

@Repository
public interface DespesaInvestimentoRepository extends JpaRepository<DespesaInvestimento, Integer> {

    @Query(value = "select (case when a.tipo = 'D' then 'Despesa' when a.tipo = 'I' then 'Investimento' else '' end) as tipo, * from despesainvestimento a inner join patrimonio b on (a.patrimonioid = b.id) inner join produto c on (c.id = b.produtoid)", nativeQuery = true)
    public List<DespesaInvestimento> AtualizarTabela();

}