package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.model.LancamentoContabil;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface LancamentoContabilRepository extends JpaRepository<LancamentoContabil, Integer> {

    @Query("SELECT c FROM lancamentocontabil c WHERE c.historico LIKE %?1%")
    public List<LancamentoContabil> pesquisar(String historico);

    @Query("SELECT c FROM conta c WHERE c.sintetica = false")
    public List<Conta> pesquisarAnalit();
   
}