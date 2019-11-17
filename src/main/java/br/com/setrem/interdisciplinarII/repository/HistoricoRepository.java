package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    @Query("SELECT e FROM historico e WHERE e.descricao LIKE %?1%")
    public List<Historico> pesquisar(String descricao);

    @Query(value = "SELECT id, descricao, REPLACE (REPLACE (c.historico, '{DATA}',  TO_CHAR(current_date, 'DD/MM/YYYY')), "
            + "'{HORA}', TO_CHAR(current_timestamp, 'HH24:MI:SS')) historico FROM historico c WHERE c.descricao = ?1 limit 1", nativeQuery = true)
    public Historico trazHistorico(String nome);
}
