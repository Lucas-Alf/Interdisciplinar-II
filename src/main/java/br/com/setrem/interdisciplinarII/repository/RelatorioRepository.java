package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Relatorio;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Integer> {
    @Query(value = "SELECT * FROM relatorio WHERE excluido = false and nome like %:descricao% and cliforid = :cliforid order by id", nativeQuery = true)
    List<Relatorio> Lista(@Param("descricao") String descricao, @Param("cliforid") String cliforid);

    @Query(value = "select descricao, sum(valor) as valortotal, tipo from conta inner join lancamentocontabil on lancamentocontabil.idconta = conta.id group by descricao,tipo order by tipo, descricao", nativeQuery = true)
    List<?> RealizaConsultaBalanceteVerificacao();
}