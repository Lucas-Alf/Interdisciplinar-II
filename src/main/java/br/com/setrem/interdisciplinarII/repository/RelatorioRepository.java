package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Relatorio;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Integer> {
    @Query(value = "SELECT * FROM relatorio WHERE nome like '%?0%' and cliforid = ?1", nativeQuery = true)
    List<Relatorio> Lista(String descricao, String cliforid);
}