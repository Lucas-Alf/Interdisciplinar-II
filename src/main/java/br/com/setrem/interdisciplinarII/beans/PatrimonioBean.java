package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.model.GrupoBem;
import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;
import br.com.setrem.interdisciplinarII.repository.PatrimonioRepository;

@Named(value = "patrimonioBean")
@SessionScoped
public class PatrimonioBean implements Serializable {

    @Autowired
    private PatrimonioRepository patrimonioRepository;
    private Patrimonio patrimonio = new Patrimonio();
    private Produto produto = new Produto();
    private GrupoBem grupoBem = new GrupoBem();
    private EstadoConservacao estadoConservacao = new EstadoConservacao();

    private List<Patrimonio> patrimonios;

    public PatrimonioBean() {

    }

    public void AtualizarTabela() {
        this.patrimonios = patrimonioRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.patrimonios = patrimonioRepository.pesquisar(descricao);
    }

    public void ListaPatrimonio() {
        this.patrimonios = patrimonioRepository.listaPatrimonio();
    }

    public void AbrirModal() {
        this.patrimonio = new Patrimonio();
        PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
    }

    public void Salvar() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        patrimonio.setCliForid(empresa);
        patrimonioRepository.save(this.patrimonio);
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
            patrimonioRepository.deleteById(id);
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

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public void setPatrimonios(List<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
    }

    /*
     * public List<Produto> getProdutos() { return produtos; }
     * 
     * public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
     */

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public GrupoBem getGrupoBem() {
        return grupoBem;
    }

    public void setGrupoBem(GrupoBem grupoBem) {
        this.grupoBem = grupoBem;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

}