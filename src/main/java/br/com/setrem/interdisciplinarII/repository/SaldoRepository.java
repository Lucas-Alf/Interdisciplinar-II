
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Saldo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer> {

    @Query(value = "select * from saldo where produtoid = ?1 and localid = ?2", nativeQuery = true)
    public List<Saldo> BuscarSaldo(int produto, int local);

    @Query(value = "select (valor * qtde) as total, * from saldo where produtoid = ?1 order by total desc limit 1", nativeQuery = true)
    public List<Saldo> BuscaProdutoMaiorSaldo(int produto);

    @Query(value = "select (select nome from produto where id = produtoid) as nome, * from saldo", nativeQuery = true)
    public List<Saldo> ListarProdutosEstoque();

}