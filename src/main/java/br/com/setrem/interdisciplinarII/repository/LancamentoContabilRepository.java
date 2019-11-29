package br.com.setrem.interdisciplinarII.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.model.Historico;
import br.com.setrem.interdisciplinarII.model.LancamentoContabil;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface LancamentoContabilRepository extends JpaRepository<LancamentoContabil, Integer> {

    @Query(value="SELECT * FROM lancamentocontabil c WHERE c.historico LIKE %?1% AND c.cliforid = ?2 ORDER BY datahora, id", nativeQuery = true)
    public List<LancamentoContabil> pesquisar(String historico,String id);

    @Query(value="SELECT * FROM lancamentocontabil c WHERE c.cliforid = ?1 ORDER BY datahora, id", nativeQuery = true)
    public List<LancamentoContabil> findByCliForid(String id);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO lancamentocontabil (valor, centrocustoid, historico, cliforid, tipo, idconta, datahora) values (?1, ?2, ?3, ?4,?5, ?6, ?7)", nativeQuery = true)
    public void insert(double valor, int centrocustoid, String historico, String clifoid, String tipo, int idconta, Date datahora);
    
}