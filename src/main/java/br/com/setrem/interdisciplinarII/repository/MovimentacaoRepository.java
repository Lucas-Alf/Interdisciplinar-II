package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Movimentacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

   @Query("SELECT coalesce(max(id), 0)+1 FROM movimentacao")
   public int maxId();

   @Query(value = "select * from movimentacao where empresaid = ?1", nativeQuery = true)
   public List<Movimentacao> AtualizarTabela(String empresa);

   @Query(value = "select * from movimentacao where empresaid = ?1 and tipo = 'V'", nativeQuery = true)
   public List<Movimentacao> AtualizarTabelaSaida(String empresa);
   
}