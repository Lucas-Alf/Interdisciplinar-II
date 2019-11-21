package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.BaixaBem;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.repository.BaixaBemRepository;
import br.com.setrem.interdisciplinarII.repository.PatrimonioRepository;

@Named(value = "baixaBemBean")
@SessionScoped
public class BaixaBemBean implements Serializable {

    @Autowired
    private BaixaBemRepository baixaBemRepository;
    private BaixaBem baixaBem = new BaixaBem();
    private Patrimonio patrimonio = new Patrimonio();
    @Autowired
    private PatrimonioRepository patrimonioRepository;

    private List<BaixaBem> baixaBens;
    //private List<Patrimonio> patrimonios;

    public BaixaBemBean() {

    }

    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.baixaBens = baixaBemRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.baixaBens = baixaBemRepository.Pesquisar(descricao, empresa.getId());
    }

    public void AbrirModal() {
        this.baixaBem = new BaixaBem();
        PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
    }

    public void Salvar() {
        if (this.baixaBem.getData() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Data.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        } else if (this.baixaBem.getPatrimonioid() == null) {    
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Patrimônio.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        } else if (this.baixaBem.getMotivobaixaid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Motivo de Baixa.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        } else if ((int)(this.baixaBem.getValor()) == 0 && this.baixaBem.getMotivobaixaid().getId() == 1) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Valor da Venda.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.patrimonio = patrimonioRepository.getOne(this.baixaBem.getPatrimonioid().getId());
            this.patrimonio.setBaixado(1);
            patrimonioRepository.save(patrimonio);
            this.baixaBem.setCliForid(empresa);
            baixaBemRepository.save(this.baixaBem);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
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

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public PatrimonioRepository getPatrimonioRepository() {
        return patrimonioRepository;
    }

    public void setPatrimonioRepository(PatrimonioRepository patrimonioRepository) {
        this.patrimonioRepository = patrimonioRepository;
    }
    
}