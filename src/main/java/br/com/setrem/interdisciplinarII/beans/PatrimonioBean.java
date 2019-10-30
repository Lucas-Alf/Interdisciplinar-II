package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.repository.PatrimonioRepository;

@Named(value = "patrimonioBean")
@SessionScoped
public class PatrimonioBean implements Serializable {

    @Autowired
    private PatrimonioRepository patrimonioRepository;
    private Patrimonio patrimonio = new Patrimonio();

    //private int id;
    //private String descricao;
    private List<Patrimonio> patrimonios;

    public PatrimonioBean() {

    }

    public void AtualizarTabela() {
        this.patrimonios = patrimonioRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.patrimonios = patrimonioRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.patrimonio = new Patrimonio();
        PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
    }

    public void Salvar() {
        /*if (this.patrimonio.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {*/
            patrimonioRepository.save(this.patrimonio);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        //}
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            patrimonioRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro deletado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            patrimonio = patrimonioRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        }
    }

    public void Alterar() {
        patrimonioRepository.save(patrimonio);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

}