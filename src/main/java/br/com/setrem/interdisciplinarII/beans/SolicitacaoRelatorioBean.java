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

import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.SolicitacaoRelatorio;
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

    private String nome;

    private List<SolicitacaoRelatorio> listaSolicitacaoRelatorio;

    public SolicitacaoRelatorioBean() {

    }

    public void AtualizarTabela(String nome, String cliforid) {
        this.listaSolicitacaoRelatorio = solicitacaoRelatorioRepository.listar(nome, cliforid);
    }

    public void Salvar(String mensagem, Integer relatorioid, Integer tipo) {
        try {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .get("empresa");
            SolicitacaoRelatorio solicitacao = new SolicitacaoRelatorio();
            solicitacao.setCliForid(empresa.getId());
            solicitacao.setDescricao(mensagem);
            solicitacao.setRelatorioid(relatorioid);
            solicitacao.setTipo(tipo);
            solicitacaoRelatorioRepository.save(solicitacao);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível salvar a solicitação."));
        }
    }
}
