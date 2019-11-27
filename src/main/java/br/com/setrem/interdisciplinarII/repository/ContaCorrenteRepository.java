
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.ContaCorrente;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrenteRepository, Integer> {

    @Query(value = "select * from contacorrente where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<ContaCorrente> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from contacorrente where cliforid = ?1", nativeQuery = true)
    public List<ContaCorrente> AtualizarTabela(String empresa);

}