/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.repository.RelatorioRepository;

@Named(value = "relatorioBean")
@SessionScoped
public class RelatorioBean implements Serializable {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public RelatorioBean() {
    }

    public List<Relatorio> Lista() {
        return relatorioRepository.findAll();
    }
}