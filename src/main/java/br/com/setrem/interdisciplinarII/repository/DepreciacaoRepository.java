package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Depreciacao;

@Repository
public interface DepreciacaoRepository extends JpaRepository<Depreciacao, Integer> {

    @Query(value = "select * from depreciacao", nativeQuery = true)
    public List<Depreciacao> AtualizarTabela();

    @Query(value = "select count(*) from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public int VerificaDepreciacao(int id);

}