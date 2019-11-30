package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.CliFor;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.ContaPagar;
import br.com.setrem.interdisciplinarII.repository.ContaPagarRepository;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "contapagarBean")
@SessionScoped
public class ContaPagarBean implements Serializable {

    @Autowired
    private ContaPagarRepository contapagarRepository;
    private ContaPagar contapagar = new ContaPagar();

    private int id;
    private String descricao;
    private double numdocumento;
    private double seriedocumento;
    private Date datadocumento;
    private Date datavencimento;
    private Date datapagamento;
    private double valor;
    private double saldo;
    private List<ContaPagar> contaspagar;

    public ContaPagarBean() {

    }
    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contaspagar = contapagarRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contaspagar = contapagarRepository.Pesquisar(descricao, empresa.getId());
    }

    public void AbrirModal() {
        this.contapagar = new ContaPagar();
        PrimeFaces.current().executeScript("$('#CadastrarContaPagar').modal('show');");
    }

    public void Salvar() {
        if (this.contapagar.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma descrição.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getNumdocumento() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o número do documento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getSeriedocumento() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o número da série da nota.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getDatadocumento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de entrada.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getDatavencimento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de vencimento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getDatapagamento() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a data de pagamento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getValor() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        }else if (this.contapagar.getContaid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a conta contábil.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.contapagar.getSaldo() == 0) {
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            contapagar.setCliForid(empresa);
            contapagarRepository.save(this.contapagar);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarContaPagar').modal('hide');");

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
                contapagarRepository.deleteById(id);
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
            contapagar = contapagarRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarContaPagar').modal('show');");
        }
    }

    public ContaPagar getContaPagar() {
        return contapagar;
    }

    public void setContaPagar(ContaPagar contaPagar) {
        this.contapagar = contapagar;
    }

    public List<ContaPagar> getContasPagar() {
        if (this.contaspagar == null) {
            this.contaspagar = contapagarRepository.findAll();
        }
        return contaspagar;
    }

    public void setContasPagar(List<ContaPagar> contaspagar) {
        this.contaspagar = contaspagar;
    }

    public ContaPagarRepository getContapagarRepository() {
        return contapagarRepository;
    }

    public void setContapagarRepository(ContaPagarRepository contapagarRepository) {
        this.contapagarRepository = contapagarRepository;
    }

    public ContaPagar getContapagar() {
        return contapagar;
    }

    public void setContapagar(ContaPagar contapagar) {
        this.contapagar = contapagar;
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

    public double getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(double numdocumento) {
        this.numdocumento = numdocumento;
    }

    public double getSeriedocumento() {
        return seriedocumento;
    }

    public void setSeriedocumento(double seriedocumento) {
        this.seriedocumento = seriedocumento;
    }

    public Date getDatadocumento() {
        return datadocumento;
    }

    public void setDatadocumento(Date datadocumento) {
        this.datadocumento = datadocumento;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<ContaPagar> getContaspagar() {
        return contaspagar;
    }

    public void setContaspagar(List<ContaPagar> contaspagar) {
        this.contaspagar = contaspagar;
    }

    }
