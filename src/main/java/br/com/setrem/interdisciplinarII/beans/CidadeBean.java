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
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            cidadeRepository.save(this.cidade);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void Deletar(int id) {
        try {
            if (id == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            } else {
                cidadeRepository.deleteById(id);
                this.AtualizarTabela();
            }
        } catch (Exception e) {
            if (e.getMessage().contains("could not extract ResultSet")) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!","Não é possível excluir este registro, pois está relacionado!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            }
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            cidade = cidadeRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarCidade').modal('show');");
        }
    }

    public void Alterar() {
        cidadeRepository.save(cidade);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
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