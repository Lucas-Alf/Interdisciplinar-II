package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.ContaPagar;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.ContaReceber;
import br.com.setrem.interdisciplinarII.repository.ContaReceberRepository;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "contareceberBean")
@SessionScoped
public class ContaReceberBean implements Serializable {

    @Autowired
    private ContaReceberRepository contareceberRepository;
    private ContaReceber contareceber = new ContaReceber();

    private int id;
    private String descricao;
    private double numdocumento;
    private Date datadocumento;
    private Date datavencimento;
    private Date datarecebimento;
    private double valor;
    private double saldo;
    private List<ContaReceber> contasreceber;

    public ContaReceberBean() {
    }

    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contasreceber = contareceberRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contasreceber = contareceberRepository.Pesquisar(descricao, empresa.getId());
    }

    public void AbrirModal() {
        this.contareceber = new ContaReceber();
        PrimeFaces.current().executeScript("$('#CadastrarContaReceber').modal('show');");
    }

    public void Salvar() {
        if (this.contareceber.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma descrição.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getNumdocumento() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o número do documento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getDatadocumento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de entrada.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getDatavencimento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de vencimento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getDatarecebimento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de recebimento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getValor() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contareceber.getSaldo() == 0) {
            saldo = valor;
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            contareceber.setCliForid(empresa);
            contareceberRepository.save(this.contareceber);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarContaReceber').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void Deletar(int id) {
        try {
            if (id == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            } else {
                contareceberRepository.deleteById(id);
                this.AtualizarTabela();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!", "Não é possível excluir, pois possui relação com outro registro.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            contareceber = contareceberRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarContaReceber').modal('show');");
        }
    }

    public ContaReceber getContaReceber() {
        return contareceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contareceber = contareceber;
    }

    public List<ContaReceber> getContasReceber() {
        if (this.contasreceber == null) {
            this.contasreceber = contareceberRepository.findAll();
        }
        return contasreceber;
    }

    public void setContasReceber(List<ContaReceber> contasreceber) {
        this.contasreceber = contasreceber;
    }

    public ContaReceberRepository getContareceberRepository() {
        return contareceberRepository;
    }

    public ContaReceber getContareceber() {
        return contareceber;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNumdocumento() {
        return numdocumento;
    }

    public Date getDatadocumento() {
        return datadocumento;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public Date getDatarecebimento() {
        return datarecebimento;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldo() {
        return saldo;
    }
}
