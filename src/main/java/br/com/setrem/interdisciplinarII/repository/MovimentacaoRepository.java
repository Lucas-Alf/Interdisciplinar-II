package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Movimentacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

   // @Query("SELECT c FROM movimentacao c WHERE c.nome LIKE %?1%")
   // public List<Movimentacao> pesquisar(String nome);
   
}