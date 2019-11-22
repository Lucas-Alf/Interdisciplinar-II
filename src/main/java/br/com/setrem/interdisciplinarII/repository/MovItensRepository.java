package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.MovItens;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MovItensRepository extends JpaRepository<MovItens, Integer> {

    @Query(value = "select * from movitens a inner join movimentacao b on (a.movimentacaoid = b.id) where a.cliforid = ?1", nativeQuery = true)
    public List<MovItens> AtualizarTabela(String empresa);

    @Query(value = "select * from movitens where movimentacaoid = ?1", nativeQuery = true)
    public List<MovItens> ListarProdutos(int id);
   
}