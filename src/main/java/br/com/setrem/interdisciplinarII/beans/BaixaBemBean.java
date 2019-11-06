package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.BaixaBem;
import br.com.setrem.interdisciplinarII.repository.BaixaBemRepository;

@Named(value = "baixabemBean")
@SessionScoped
public class BaixaBemBean implements Serializable {

    @Autowired
    private BaixaBemRepository baixaBemRepository;
    private BaixaBem baixaBem = new BaixaBem();

    private List<BaixaBem> baixaBens;

    public BaixaBemBean() {

    }

    public void AtualizarTabela() {
        this.baixaBens = baixaBemRepository.findAll();
    }

    /*public void Pesquisar(String descricao) {
        this.baixaBens = baixaBemRepository.pesquisar(descricao);
    }*/

    public void AbrirModal() {
        this.baixaBem = new BaixaBem();
        PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
    }

    public void Salvar() {
        baixaBemRepository.save(this.baixaBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            baixaBemRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro deletado.");
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
            baixaBem = baixaBemRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        }
    }

    public void Alterar() {
        baixaBemRepository.save(baixaBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public BaixaBemRepository getBaixaBemRepository() {
        return baixaBemRepository;
    }

    public void setBaixaBemRepository(BaixaBemRepository baixaBemRepository) {
        this.baixaBemRepository = baixaBemRepository;
    }

    public BaixaBem getBaixaBem() {
        return baixaBem;
    }

    public void setBaixaBem(BaixaBem baixaBem) {
        this.baixaBem = baixaBem;
    }

    public List<BaixaBem> getBaixaBens() {
        return baixaBens;
    }

    public void setBaixaBens(List<BaixaBem> baixaBens) {
        this.baixaBens = baixaBens;
    }

}