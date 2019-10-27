/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.FiltroRelatorio;
import br.com.setrem.interdisciplinarII.repository.FiltroRelatorioRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lukin
 */
@Named(value = "filtroRelatorioBean")
@SessionScoped
public class FiltroRelatorioBean implements Serializable {

    @Autowired
    private FiltroRelatorioRepository filtroRelatorioRepository;

    private List<FiltroRelatorio> filtroRelatorio;

    public FiltroRelatorioBean() {

    }

    public void AtualizarTabela(Integer relatorioId) {
        this.filtroRelatorio = filtroRelatorioRepository.listar(relatorioId);
    }
}
