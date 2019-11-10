package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.model.LancamentoContabil;
import br.com.setrem.interdisciplinarII.repository.LancamentoContabilRepository;

@Named(value = "lancamentoContabilBean")
@SessionScoped
public class LancamentoContabilBean implements Serializable {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    private LancamentoContabil lancamentoContabil = new LancamentoContabil();

    private List<Conta> contasAnalit;

    private List<LancamentoContabil> lancamentoContabils;

    public LancamentoContabilBean() {
    }

    public void Insert() throws ParseException {
        if(this.lancamentoContabil.getHistorico().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Histórico!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(this.lancamentoContabil.getValor() <= 0){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Valor!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(this.lancamentoContabil.getDatahora().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Data!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(this.lancamentoContabil.getTipo().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Tipo!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(this.lancamentoContabil.getCentrocustoid().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Centro de Custo!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } if(this.lancamentoContabil.getIdconta().equals("")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Conta!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarConta').modal('show');");
        } else {
            CliFor empresa = (CliFor)
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.lancamentoContabil.setCliforid(empresa);
            lancamentoContabilRepository.save(this.lancamentoContabil);
            this.AtualizarTable();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
            "Salvo com sucesso.");

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void LancamentoContabil(List<LancamentoContabil> listaLancamento,int historicoPadrao) {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
          //  this.lancamentoContabil.setCliforid(empresa);

           // LancamentoContabil lan = new LancamentoContabil();
            //VER COM O PROFESSOR COMO VAI FUNCIONAR O HISTORICO PADRAO NOS MODULOS (CRIAR TELA DE PARAMETRIZAÇÃO? OU PEGAR AUTOMATICO CONFORME O NOME?).    

            listaLancamento.forEach(item-> lancamentoContabilRepository.save(item));
    }

    public void PesquisarAnalitica() {
        this.contasAnalit = lancamentoContabilRepository.pesquisarAnalit();
    }

    public List<LancamentoContabil> ListarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        return lancamentoContabilRepository.findByCliForid(empresa.getId());
    }

    public void AtualizarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.lancamentoContabils = lancamentoContabilRepository.findByCliForid(empresa.getId());
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
            PrimeFaces.current().executeScript("$('#CadastrarLancamento').modal('show');");
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

    public void Pesquisar(String historico) {
        this.lancamentoContabils = lancamentoContabilRepository.pesquisar(historico);
    }

    public void AbrirModal() {
        this.lancamentoContabil = new LancamentoContabil();
        PrimeFaces.current().executeScript("$('#CadastrarLancamento').modal('show');");
    }

    public LancamentoContabil getLancamentoContabil() {
        return lancamentoContabil;
    }

    public void setLancamentoContabil(LancamentoContabil lancamentoContabil) {
        this.lancamentoContabil = lancamentoContabil;
    }

    public List<LancamentoContabil> getLancamentoContabils() {
        if (this.lancamentoContabils == null) {
            this.lancamentoContabils = lancamentoContabilRepository.findAll();
        }
        return lancamentoContabils;
    }

    public void setLancamentoContabils(List<LancamentoContabil> lancamentoContabils) {
        this.lancamentoContabils = lancamentoContabils;
    }

    public List<Conta> getContasAnalit() {
        return contasAnalit;
    }

    public void setContasAnalit(List<Conta> contasAnalit) {
        this.contasAnalit = contasAnalit;
    }

}
