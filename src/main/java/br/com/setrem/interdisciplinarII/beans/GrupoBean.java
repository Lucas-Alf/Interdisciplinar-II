package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Grupo;
import br.com.setrem.interdisciplinarII.repository.GrupoRepository;

@Named(value = "grupoBean")
@SessionScoped
public class GrupoBean implements Serializable {

    @Autowired
    private GrupoRepository grupoRepository;
    private Grupo grupo = new Grupo();

    private int id;
    private String descricao;
    private List<Grupo> grupos;

    public GrupoBean() {

    }

    public void AtualizarTabela() {
        this.grupos = grupoRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.grupos = grupoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.grupo = new Grupo();
        PrimeFaces.current().executeScript("$('#CadastrarGrupo').modal('show');");
    }

    public void Salvar() {
        if (this.grupo.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        } else {
            grupoRepository.save(this.grupo);
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
            grupoRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            grupo = grupoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarGrupo').modal('show');");
        }
    }

    public void Alterar() {
        grupoRepository.save(grupo);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setgrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public List<Grupo> getGrupos() {
        if (this.grupos == null) {
            this.grupos = grupoRepository.findAll();
        }
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

}