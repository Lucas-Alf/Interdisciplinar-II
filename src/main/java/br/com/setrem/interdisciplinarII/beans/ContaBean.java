package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.ContaRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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

    public void Insert(String descricao, BigDecimal valor, Conta contapai) {
        Conta conta = new Conta();
        conta.setDescricao(descricao);
        conta.setValor(valor);
        conta.setContaPai(contapai);
        //centCust.setCliForid(CliForid);
        PrimeFaces.current().executeScript("$('#CadastrarConta').modal('hide');");
        contaRepository.save(conta);
        this.AtualizarTable();
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
            int codigo = conta.getId();
            String descricao = conta.getDescricao();
            BigDecimal valor = conta.getValor();
            Conta contapai = conta.getContaPai();
            conta.setId(codigo);
            conta.setDescricao(descricao);
            conta.setValor(valor);
            conta.setContaPai(contapai);
            PrimeFaces.current().executeScript("$('#AlterarConta').modal('hide');");
        }
    }

    public void Alterar() {
        contaRepository.save(conta);
        this.AtualizarTable(); 
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
