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
            context.addMessage("validacao", fm);
        } if(!this.conta.isSintetica() && this.conta.getContapai() == null){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Quando a conta for analítica, é obrigatório informar o Pai!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.conta.setCliforid(empresa);
            contaRepository.save(this.conta);
            this.AtualizarTable();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public List<Conta> ListarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        return contaRepository.pessquisarGrid(empresa.getId(),"");
    }

    public void AtualizarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contas = contaRepository.pessquisarGrid(empresa.getId(),"");
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            try {
                contaRepository.deleteById(id);
                this.AtualizarTable();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Registro é utilizado por Lançamento(s) Contábil(eis).");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            }
        }

    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            this.PesquisarSintetica();
            conta = contaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        }
    }

    public void Pesquisar(String nome) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contas = contaRepository.pessquisarGrid(empresa.getId(),nome);
    }

    public void PesquisarSintetica() { //ORGANIZAR A LISTA POR HIERARQUIA, FICA CANSATIVO TERQUE ACHAR A CONTA PAI
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.contasSint = contaRepository.pesquisarSint(empresa.getId());
    }

    public void AbrirModal() {
        this.PesquisarSintetica();
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
