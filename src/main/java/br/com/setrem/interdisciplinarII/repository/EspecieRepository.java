
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Especie;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer> {

    @Query(value = "select * from especie where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Especie> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from especie where cliforid = ?1", nativeQuery = true)
    public List<Especie> AtualizarTabela(String empresa);

}