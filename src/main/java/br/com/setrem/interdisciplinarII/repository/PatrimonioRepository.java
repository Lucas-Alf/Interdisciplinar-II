package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {

    @Query(value = "select * from patrimonio a inner join produto b on (a.produtoid = b.id) where a.baixado = 0 and b.nome like %?1% and a.cliforid = ?2", nativeQuery = true)
    public List<Patrimonio> pesquisar(String descricao, String empresa);

    @Query(value = "select * from patrimonio a inner join produto b on (a.produtoid = b.id) where a.baixado = 0 and a.cliforid = ?1", nativeQuery = true)
    public List<Patrimonio> listaPatrimonio(String empresa);
    
    @Query("update patrimonio e set e.baixado = 1 where e.id = ?1 and cliforid = ?2")
    public void BaixarBem(int id, String empresa);
}