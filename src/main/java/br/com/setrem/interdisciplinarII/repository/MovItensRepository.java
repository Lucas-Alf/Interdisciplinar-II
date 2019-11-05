package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.MovItens;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovItensRepository extends JpaRepository<MovItens, Integer> {

    @Query("SELECT c FROM compra c WHERE c.nome LIKE %?1%")
    public List<MovItens> pesquisar(String nome);
   
}