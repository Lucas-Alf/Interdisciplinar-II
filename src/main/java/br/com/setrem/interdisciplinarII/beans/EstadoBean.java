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

import br.com.setrem.interdisciplinarII.model.Estado;
import br.com.setrem.interdisciplinarII.repository.EstadoRepository;

@Named(value = "estadoBean")
@SessionScoped
public class EstadoBean implements Serializable {

    @Autowired
    private EstadoRepository estadoRepository;
    private Estado estado = new Estado();

    private int id;
    private String descricao;
    private String sigla;
    private List<Estado> estados;
    
    public EstadoBean() {

    }

    public void AtualizarTabela() {
        this.estados = estadoRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.estados = estadoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.estado = new Estado();
        PrimeFaces.current().executeScript("$('#CadastrarEstado').modal('show');");
    }

    public void Salvar() {
        if (this.estado.getDescricao().equals("") || this.estado.getSigla().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe Todos os Campos.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);

            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarEstado').modal('show');");
        } else {
            estadoRepository.save(this.estado);
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
            estadoRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            estado = estadoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarEstado').modal('show');");
        }
    }

    public void Alterar() {
        estadoRepository.save(estado);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {  
        this.estados = estados;
    }

}