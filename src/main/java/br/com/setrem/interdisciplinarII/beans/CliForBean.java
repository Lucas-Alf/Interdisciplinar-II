package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.repository.CliForRepository;

@Named(value = "cliforBean")
@SessionScoped
public class CliForBean implements Serializable {

    @Autowired
    private CliForRepository cliforRepository;
    private CliFor clientForn = new CliFor();

    private List<CliFor> clifors;

    public CliForBean() {
    }

    public void Insert() {
        if (this.clientForn.getNome().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um nome!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        } else {
            cliforRepository.save(this.clientForn);
            this.AtualizarTable();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public List<CliFor> AtualizarTable() {
        return cliforRepository.findAll();
    }

    public List<CliFor> listaEmpresas() {
        List<CliFor> lista = cliforRepository.ListaEmpresa();
        return lista;
    }

    public void Remove(String id) {
        if (id == "") {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione um registro para excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);

        } else {
            cliforRepository.deleteById(id);
        }
    }

    public void AbreAlterar(String id) {
        if (id.equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            clientForn = cliforRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarCliFor').modal('show');");
        }
    }

    public void Alterar() {
        cliforRepository.save(clientForn);
        this.AtualizarTable();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public void Pesquisar(String nome) {
         this.clifors = cliforRepository.pesquisar(nome);
    }

    public void AbrirModal() {
        this.clientForn = new CliFor();
        PrimeFaces.current().executeScript("$('#CadastrarCliFor').modal('show');");
    }

    public List<CliFor> getClifors() {
        return clifors;
    }

    public void setClifors(List<CliFor> clifors) {
        this.clifors = clifors;
    }

    
}
