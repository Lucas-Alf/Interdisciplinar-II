/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Marca;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query(value = "select * from marca where nome like %?1% and cliforid = ?2", nativeQuery = true)
    public List<Marca> Pesquisar(String nome, String empresa);

    @Query(value = "select * from marca where cliforid = ?1", nativeQuery = true)
    public List<Marca> AtualizarTabela(String empresa);
    
}