package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.GrupoBem;
import br.com.setrem.interdisciplinarII.repository.GrupoBemRepository;

@Named(value = "grupoBemBean")
@SessionScoped
public class GrupoBemBean implements Serializable {

    @Autowired
    private GrupoBemRepository grupoBemRepository;
    private GrupoBem grupoBem = new GrupoBem();

    private int id;
    private String descricao;
    private BigDecimal taxadepreciacao;
    private BigDecimal vidautil;
    private List<GrupoBem> grupoBens;

    public GrupoBemBean() {

    }

    public void AtualizarTabela() {
        this.grupoBens = grupoBemRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.grupoBens = grupoBemRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.grupoBem = new GrupoBem();
        PrimeFaces.current().executeScript("$('#CadastrarGrupoBem').modal('show');");
    }

    public void Salvar() {
        if (this.grupoBem.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            grupoBemRepository.save(this.grupoBem);
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
            grupoBemRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            grupoBem = grupoBemRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarGrupoBem').modal('show');");
        }
    }

    public void Alterar() {
        grupoBemRepository.save(grupoBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public GrupoBem getGrupoBem() {
        return grupoBem;
    }

    public void setGrupoBem(GrupoBem grupoBem) {
        this.grupoBem = grupoBem;
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

    public BigDecimal getTaxadepreciacao() {
        return taxadepreciacao;
    }

    public void setTaxadepreciacao(BigDecimal taxadepreciacao) {
        this.taxadepreciacao = taxadepreciacao;
    }

    public BigDecimal getVidautil() {
        return vidautil;
    }

    public void setVidautil(BigDecimal vidautil) {
        this.vidautil = vidautil;
    }

    public List<GrupoBem> getGrupoBens() {
        return grupoBens;
    }

    public void setGrupoBens(List<GrupoBem> grupoBens) {
        this.grupoBens = grupoBens;
    }

}