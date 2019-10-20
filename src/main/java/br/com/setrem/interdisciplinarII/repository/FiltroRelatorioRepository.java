/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.FiltroRelatorio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FiltroRelatorioRepository extends JpaRepository<FiltroRelatorio, Integer> {
    @Query(value = "SELECT * FROM filtrorelatorio WHERE nome LIKE %?1% and relatorioid = ?2", nativeQuery = true)
    public List<FiltroRelatorio> listar(String descricao, Integer relatorioId);
}
