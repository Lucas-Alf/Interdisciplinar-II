
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Saldo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer> {

    @Query(value = "select * from saldo where produtoid = ?1", nativeQuery = true)
    public List<Saldo> BuscarSaldo(int produto);

}