package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
   
}