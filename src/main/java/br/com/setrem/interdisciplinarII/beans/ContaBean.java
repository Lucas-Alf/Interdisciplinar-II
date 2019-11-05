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
    private boolean sintetica;
    private Conta contapai;
    private List<Conta> contas;
    private List<Conta> contasSint;

    public ContaBean() {
    }

    public void Insert() {
        if (this.conta.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma descriçao!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(!this.conta.isSintetica() && this.conta.getContapai() == null){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Quando a conta for analítica, é obrigatório informar o Pai!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.conta.setCliforid(empresa);
            contaRepository.save(this.conta);
            this.AtualizarTable();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
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

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
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
            conta = contaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        }
    }

    public void Alterar() {
        contaRepository.save(conta);
        this.AtualizarTable(); 
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public void Pesquisar(String nome) {
         this.contas = contaRepository.pesquisar(nome);
    }

    public void PesquisarSintetica() {
        this.contasSint = contaRepository.pesquisarSint();
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

    public boolean isSintetica() {
        return sintetica;
    }

    public void setSintetica(boolean sintetica) {
        this.sintetica = sintetica;
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

    public List<Conta> getContasSint() {
        return contasSint;
    }

    public void setContasSint(List<Conta> contasSint) {
        this.contasSint = contasSint;
    }

}
