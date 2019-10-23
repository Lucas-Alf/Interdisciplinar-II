package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Local;
import br.com.setrem.interdisciplinarII.repository.LocalRepository;

@Named(value = "localBean")
@SessionScoped
public class LocalBean implements Serializable {

    @Autowired
    private LocalRepository localRepository;
    private Local local = new Local();

    private int id;
    private String nome;
    private String prateleira;
    private String estante;
    private List<Local> locais;

    public LocalBean() {

    }

    public void AtualizarTabela() {
        this.locais = localRepository.findAll();
    }

    public void Pesquisar(String nome) {
        this.locais = localRepository.pesquisar(nome);
    }

    public void AbrirModal() {
        this.local = new Local();
        PrimeFaces.current().executeScript("$('#CadastrarLocal').modal('show');");
    }

    public void Salvar() {
        if (this.local.getNome().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            localRepository.save(this.local);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            localRepository.deleteById(id);
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
            local = localRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarLocal').modal('show');");
        }
    }

    public void Alterar() {
        localRepository.save(local);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

}