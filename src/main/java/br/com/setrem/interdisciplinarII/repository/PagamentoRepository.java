
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Pagamento;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query(value = "select * from pagamento where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Pagamento> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from pagamento where cliforid = ?1", nativeQuery = true)
    public List<Pagamento> AtualizarTabela(String empresa);

}