
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Recebimento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Integer> {

    @Query(value = "select * from recebimento where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Recebimento> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from recebimento where cliforid = ?1", nativeQuery = true)
    public List<Recebimento> AtualizarTabela(String empresa);

}