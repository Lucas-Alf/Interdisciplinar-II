
package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

    @Query(value = "select * from local where descricao like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Local> Pesquisar(String nome, String empresa);

    @Query(value = "select * from local where cliforid = ?1", nativeQuery = true)
    public List<Local> AtualizarTabela(String empresa);
    
}