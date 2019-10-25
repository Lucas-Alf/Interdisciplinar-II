package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.repository.EstadoConservacaoRepository;

@Named(value = "estadoConservacaoBean")
@SessionScoped
public class EstadoConservacaoBean implements Serializable {

    @Autowired
    private EstadoConservacaoRepository estadoConservacaoRepository;
    private EstadoConservacao estadoConservacao = new EstadoConservacao();

    private int id;
    private String descricao;
    private List<EstadoConservacao> estadoConservacoes;

    public EstadoConservacaoBean() {

    }

    public void AtualizarTabela() {
        this.estadoConservacoes = estadoConservacaoRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.estadoConservacoes = estadoConservacaoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.estadoConservacao = new EstadoConservacao();
        PrimeFaces.current().executeScript("$('#CadastrarEstadoConservacao').modal('show');");
    }

    public void Salvar() {
        if (this.estadoConservacao.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estadoConservacaoRepository.save(this.estadoConservacao);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estadoConservacaoRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estadoConservacao = estadoConservacaoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarEstadoConservacao').modal('show');");
        }
    }

    public void Alterar() {
        estadoConservacaoRepository.save(estadoConservacao);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<EstadoConservacao> getEstadoConservacoes() {
        if (this.estadoConservacoes == null) {
            this.estadoConservacoes = estadoConservacaoRepository.findAll();
        }
        return estadoConservacoes;
    }

    public void setEstadoConservacoes(List<EstadoConservacao> estadoConservacoes) {
        this.estadoConservacoes = estadoConservacoes;
    }

}