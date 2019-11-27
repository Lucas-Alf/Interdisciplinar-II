
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.ContaReceber;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceberRepository, Integer> {

    @Query(value = "select * from contareceber where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<ContaReceber> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from contareceber where cliforid = ?1", nativeQuery = true)
    public List<ContaReceber> AtualizarTabela(String empresa);

}