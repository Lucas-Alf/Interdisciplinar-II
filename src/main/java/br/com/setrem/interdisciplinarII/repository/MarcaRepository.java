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

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query("SELECT e FROM marca e WHERE e.nome LIKE %?1%")
    public List<Marca> pesquisar(String descricao);

}

