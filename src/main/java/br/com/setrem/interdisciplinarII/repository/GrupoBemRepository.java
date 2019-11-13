package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.GrupoBem;

@Repository
public interface GrupoBemRepository extends JpaRepository<GrupoBem, Integer> {

    @Query("SELECT e FROM grupobem e WHERE e.descricao LIKE %?1%")
    public List<GrupoBem> pesquisar(String descricao);

}