package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Grupo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    @Query(value = "select * from grupo where descricao like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Grupo> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from grupo where cliforid = ?1", nativeQuery = true)
    public List<Grupo> AtualizarTabela(String empresa);

}