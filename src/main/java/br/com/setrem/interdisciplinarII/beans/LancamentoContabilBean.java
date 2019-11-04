package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.LancamentoContabil;
import br.com.setrem.interdisciplinarII.repository.LancamentoContabilRepository;

@Named(value = "lancamentoContabil")
@SessionScoped
public class LancamentoContabilBean implements Serializable {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    private LancamentoContabil lancamentoContabil = new LancamentoContabil();

    private List<LancamentoContabil> lancamentoContabils;

    public LancamentoContabilBean() {
    }

    public void Insert() {
        // if (this.conta.getDescricao().equals("")) {
        // FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!",
        // "Informe uma descriçao!");
        // FacesContext context = FacesContext.getCurrentInstance();
        // context.addMessage(null, fm);
        // PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        // } if(!this.conta.isSintetica() && this.conta.getContapai() == null){
        // FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!",
        // "Quando a conta for analítica, é obrigatório informar o Pai!");
        // FacesContext context = FacesContext.getCurrentInstance();
        // context.addMessage(null, fm);
        // PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        // PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        // } else {
        // CliFor empresa = (CliFor)
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        // this.conta.setCliforid(empresa);
        // contaRepository.save(this.conta);
        // this.AtualizarTable();
        // PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        // FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
        // "Salvo com sucesso.");
        // FacesContext context = FacesContext.getCurrentInstance();
        // context.addMessage(null, fm);
        // }
    }

    public List<LancamentoContabil> ListarTable() {
        return lancamentoContabilRepository.findAll();
    }

    public void AtualizarTable() {
        this.lancamentoContabils = lancamentoContabilRepository.findAll();
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            lancamentoContabilRepository.deleteById(id);
            this.AtualizarTable();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
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
            lancamentoContabil = lancamentoContabilRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        }
    }

    public void Alterar() {
        lancamentoContabilRepository.save(lancamentoContabil);
        this.AtualizarTable();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public void Pesquisar(String nome) {
        this.lancamentoContabils = lancamentoContabilRepository.pesquisar(nome);
    }

    public void AbrirModal() {
        this.lancamentoContabil = new LancamentoContabil();
        PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
    }

}
