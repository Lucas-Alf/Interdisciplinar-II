package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.CentroCusto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {

    @Query(value="SELECT * FROM centrocusto c WHERE c.nome LIKE %?1% AND c.cliforid = ?2 ORDER BY id", nativeQuery = true)
    public List<CentroCusto> pesquisar(String nome,String id);

    @Query(value="SELECT * FROM centrocusto c WHERE c.cliforid = ?1 ORDER BY id", nativeQuery = true)
    public List<CentroCusto> findByCliForid(String id);

    @Query(value="SELECT * FROM centrocusto c WHERE c.nome LIKE %?2% AND c.cliforid = ?1 ORDER BY id", nativeQuery = true)
    public CentroCusto trazCentroCusto(String id, String nome);
   
}