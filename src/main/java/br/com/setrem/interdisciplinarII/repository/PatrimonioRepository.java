package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {

    @Query("SELECT e FROM patrimonio e INNER JOIN e.produtoid t where e.baixado = 0 and t.nome LIKE %?1%")
    public List<Patrimonio> pesquisar(String descricao);

    @Query("SELECT e FROM patrimonio e INNER JOIN e.produtoid where e.baixado = 0")
    public List<Patrimonio> listaPatrimonio();

    //@Query(value = "select b.vidautil from patrimonio a inner join grupobem b on (a.grupobemid = b.id) where a.id = ?1", nativeQuery = true)
    //public List<Patrimonio> listaPatrimonio();
    
    @Query("update patrimonio e set e.baixado = 1 where e.id = ?1")
    public void BaixarBem(int id);

    @Query(value = "select b.taxadepreciacao, b.vidautil from patrimonio a inner join grupobem b on (a.grupobemid = b.id) where a.id = ?1", nativeQuery = true)
    public double BuscarDepreciacao(int patrimonio);

    @Query(value = "select b.vidautil from patrimonio a inner join grupobem b on (a.grupobemid = b.id) where a.id = ?1", nativeQuery = true)
    public double BuscarVidaUtil(int patrimonio);

}