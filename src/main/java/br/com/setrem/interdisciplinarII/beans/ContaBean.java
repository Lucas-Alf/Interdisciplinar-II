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

import br.com.setrem.interdisciplinarII.model.CentroCusto;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.repository.ContaRepository;

@Named(value = "contaBean")
@SessionScoped
public class ContaBean implements Serializable {

    @Autowired
    private ContaRepository contaRepository;
    private Conta conta =  new Conta();

    private int id;
    private String descricao;
    private CliFor CliForid;
    private BigDecimal valor;
    private Conta contapai;
    private List<Conta> contas;

    public ContaBean() {
    }

    public void Insert() {
        if (this.conta.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma descriçao!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.conta.setCliForid(empresa);
            contaRepository.save(this.conta);
            this.AtualizarTable();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public List<Conta> ListarTable() {
        return contaRepository.findAll();
    }

    public void AtualizarTable() {
        this.contas = contaRepository.findAll();
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            contaRepository.deleteById(id);
            this.AtualizarTable();
        }

    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            conta = contaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        }
    }

    public void Alterar() {
        contaRepository.save(conta);
        this.AtualizarTable(); 
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public void Pesquisar(String nome) {
         this.contas = contaRepository.pesquisar(nome);
    }

    public void AbrirModal() {
        this.conta = new Conta();
        PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
    }


    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Conta getContapai() {
        return contapai;
    }

    public void setContapai(Conta contapai) {
        this.contapai = contapai;
    }

    public List<Conta> getContas() {
        if (this.contas == null) {
            this.contas = contaRepository.findAll();
        }
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }


}
