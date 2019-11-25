package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Cidade;
import br.com.setrem.interdisciplinarII.model.Estado;
import br.com.setrem.interdisciplinarII.repository.CidadeRepository;

@Named(value = "cidadeBean")
@SessionScoped
public class CidadeBean implements Serializable {

    @Autowired
    private CidadeRepository cidadeRepository;
    private Cidade cidade = new Cidade();
    private Estado estado = new Estado();

    private int id;
    private String descricao;
    private List<Cidade> cidades;

    public CidadeBean() {

    }

    public void AtualizarTabela() {
        this.cidades = cidadeRepository.pesquisar("");
    }

    public void Pesquisar(String descricao) {
        this.cidades = cidadeRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.cidade = new Cidade();
        PrimeFaces.current().executeScript("$('#CadastrarCidade').modal('show');");
    }

    public void Salvar() {
        if (this.cidade.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.cidade.getEstadoid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Estado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else {
            cidadeRepository.save(this.cidade);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarCidade').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void Deletar(int id) {
        try {
            if (id == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            } else {
                cidadeRepository.deleteById(id);
                this.AtualizarTabela();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!","Não é possível excluir, pois possui relação com outro registro.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            cidade = cidadeRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarCidade').modal('show');");
        }
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}