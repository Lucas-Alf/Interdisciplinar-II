/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.google.gson.Gson;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Auditoria;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Relatorio;
import br.com.setrem.interdisciplinarII.model.SolicitacaoRelatorio;
import br.com.setrem.interdisciplinarII.repository.AuditoriaRepository;
import br.com.setrem.interdisciplinarII.repository.SolicitacaoRelatorioRepository;

/**
 *
 * @author lukin
 */
@Named(value = "solicitacaoRelatorioBean")
@SessionScoped
public class SolicitacaoRelatorioBean implements Serializable {

    @Autowired
    private SolicitacaoRelatorioRepository solicitacaoRelatorioRepository;
    @Autowired
    private AuditoriaRepository auditoriaRepository;

    private String nome;
    private Relatorio relatorio = new Relatorio();
    private String tipo;
    private String mensagem;

    private List<SolicitacaoRelatorio> listaSolicitacaoRelatorio;

    public SolicitacaoRelatorioBean() {

    }

    public void AtualizarTabela(String nome, String cliforid) {
        this.listaSolicitacaoRelatorio = solicitacaoRelatorioRepository.listar(nome, cliforid);
    }

    public String Salvar() {
        try {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .get("empresa");
            SolicitacaoRelatorio solicitacao = new SolicitacaoRelatorio();
            solicitacao.setCliForid(empresa.getId());
            solicitacao.setDescricao(mensagem);
            if (relatorio == null || relatorio.getId() == 0) {
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Relatório inválido."));
                return "";
            }
            if (mensagem == null || mensagem == "") {
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Mensagem inválida."));
                return "";
            }
            solicitacao.setRelatorioid(relatorio.getId());
            solicitacao.setTipo(Integer.parseInt(tipo));
            solicitacaoRelatorioRepository.save(solicitacao);
            Gson gson = new Gson();
            Auditoria audit = new Auditoria();
            audit.setTabela("solicitacaorelatorio");
            audit.setValorantigo(null);
            audit.setValornovo(gson.toJson(solicitacao));
            audit.setCliforId(empresa.getId());
            auditoriaRepository.save(audit);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso."));
            return "";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível salvar a solicitação. "));
            return "";
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
