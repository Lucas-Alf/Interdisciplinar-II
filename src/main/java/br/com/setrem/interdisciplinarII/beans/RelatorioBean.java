/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.repository.RelatorioRepository;

@Named(value = "relatorioBean")
@SessionScoped
public class RelatorioBean implements Serializable {

    @Autowired
    private RelatorioRepository relatorioRepository;

    private String descricaoBusca = "";
    private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();

    public String getDescricaoBusca() {
        return descricaoBusca;
    }

    public void setDescricaoBusca(String descricaoBusca) {
        this.descricaoBusca = descricaoBusca;
    }

    public List<Relatorio> getListaRelatorio() {
        return listaRelatorio;
    }

    public void setListaRelatorio(List<Relatorio> listaRelatorio) {
        this.listaRelatorio = listaRelatorio;
    }

    public RelatorioBean() {
    }

    public void Lista() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.listaRelatorio = relatorioRepository.Lista(this.descricaoBusca, empresa.getId());
    }
}