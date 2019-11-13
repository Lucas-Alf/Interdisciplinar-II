package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Depreciacao;
import br.com.setrem.interdisciplinarII.repository.DepreciacaoRepository;

@Named(value = "depreciacaoBean")
@SessionScoped
public class DepreciacaoBean implements Serializable {

    @Autowired
    private DepreciacaoRepository depreciacaoRepository;
    private Depreciacao depreciacao = new Depreciacao();

    private List<Depreciacao> depreciacoes;

    public DepreciacaoBean() {

    }

    public void AtualizarTabela() {
        this.depreciacoes = depreciacaoRepository.AtualizarTabela();
    }

    /*public void Pesquisar(String descricao) {
        this.depreciacoes = depreciacaoRepository.pesquisar(descricao);
    }*/

    public void AbrirModal() {
        this.depreciacao = new Depreciacao();
        PrimeFaces.current().executeScript("$('#CadastrarDepreciacao').modal('show');");
    }

    public void Salvar() {
        depreciacaoRepository.save(this.depreciacao);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Depreciado com Sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

}