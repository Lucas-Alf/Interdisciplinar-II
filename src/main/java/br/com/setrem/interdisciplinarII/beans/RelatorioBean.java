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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.FiltroRelatorio;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.repository.FiltroRelatorioRepository;
import br.com.setrem.interdisciplinarII.repository.RelatorioRepository;

@Named(value = "relatorioBean")
@SessionScoped
public class RelatorioBean implements Serializable {

    @Autowired
    private RelatorioRepository relatorioRepository;
    @Autowired
    private FiltroRelatorioRepository filtroRelatorioRepository;

    private String descricaoBusca = "";
    private List<Relatorio> listaRelatorio = new ArrayList<Relatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorio = new ArrayList<FiltroRelatorio>();
    private List<FiltroRelatorio> listaFiltroRelatorioSelecionados = new ArrayList<FiltroRelatorio>();

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

    public List<FiltroRelatorio> getListaFiltroRelatorio() {
        return listaFiltroRelatorio;
    }

    public void setListaFiltroRelatorio(List<FiltroRelatorio> listaFiltroRelatorio) {
        this.listaFiltroRelatorio = listaFiltroRelatorio;
    }

    public List<FiltroRelatorio> getListaFiltroRelatorioSelecionados() {
        return listaFiltroRelatorioSelecionados;
    }

    public void setListaFiltroRelatorioSelecionados(List<FiltroRelatorio> listaFiltroRelatorioSelecionados) {
        this.listaFiltroRelatorioSelecionados = listaFiltroRelatorioSelecionados;
    }

    public void AbreImpressao(int relatorioId) {
        if (relatorioId == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um relatório para imprimir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            this.listaFiltroRelatorio = filtroRelatorioRepository.listar(relatorioId);
            if (listaFiltroRelatorio.size() == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                        "Nenhum filtro encontrado para o relatório.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            }
            PrimeFaces.current().executeScript("$('#impressaoModal').modal('show');");
        }
    }

}