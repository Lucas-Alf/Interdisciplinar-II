package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.EstadoConservacaoRepository;
import javax.inject.Named;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.web.context.request.RequestContextListener;


@Named(value = "estadoConservacaoBean")
@SessionScoped
public class EstadoConservacaoBean implements Serializable {

    @Autowired
    private EstadoConservacaoRepository estadoConservacaoRepository;
    private EstadoConservacao estadoConservacao =  new EstadoConservacao();

    private int id;
    private String descricao;
    private List<EstadoConservacao> estadoConservacoes;

    public EstadoConservacaoBean() {

    }

    public void Salvar(String descricao) {
        EstadoConservacao est = new EstadoConservacao();
        est.setDescricao(descricao);
        PrimeFaces.current().executeScript("$('#CadastrarEstadoConservacao').modal('hide');");
        estadoConservacaoRepository.save(est);
        this.AtualizarTabela();
    }

    public List<EstadoConservacao> ListarTabela() {
        return estadoConservacaoRepository.findAll();
    }

    public void AtualizarTabela() {
        this.estadoConservacoes = estadoConservacaoRepository.findAll();
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estadoConservacaoRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estadoConservacao = estadoConservacaoRepository.getOne(id);
            int codigo = estadoConservacao.getId();
            String descricao = estadoConservacao.getDescricao();
            estadoConservacao.setId(codigo);
            estadoConservacao.setDescricao(descricao);
            PrimeFaces.current().executeScript("$('#AlterarEstadoConservacao').modal('show');");
        }
    }

    public void Alterar() {
        estadoConservacaoRepository.save(estadoConservacao);
        this.AtualizarTabela(); 
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