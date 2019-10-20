/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.SolicitacaoRelatorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRelatorioRepository extends JpaRepository<SolicitacaoRelatorio, Integer> {

    @Query(value = "SELECT * FROM solicitacaorelatorio WHERE descricao LIKE %?1% and cliforid = ?2", nativeQuery = true)
    public List<SolicitacaoRelatorio> listar(String descricao, String cliforid);
}
