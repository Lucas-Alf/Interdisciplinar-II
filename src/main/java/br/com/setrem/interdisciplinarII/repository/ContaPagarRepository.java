
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.ContaPagar;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPagarRepository  extends JpaRepository<ContaPagar, Integer> {

    /*@Query(value = "select * from contapagar where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<ContaPagar> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from contapagar where cliforid = ?1", nativeQuery = true)
    public List<ContaPagar> AtualizarTabela(String empresa);

    public List<ContaPagar> pesquisar(String descricao);*/

}