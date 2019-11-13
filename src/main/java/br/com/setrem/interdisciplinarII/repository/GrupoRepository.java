package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    @Query("SELECT e FROM grupo e WHERE e.descricao LIKE %?1%")
    public List<Grupo> pesquisar(String descricao);

}
