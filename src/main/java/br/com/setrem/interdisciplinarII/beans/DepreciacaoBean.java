package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Depreciacao;
import br.com.setrem.interdisciplinarII.repository.DepreciacaoRepository;

@Named(value = "depreciacaoBean")
@SessionScoped
public class DepreciacaoBean implements Serializable {
    
    @Autowired
    private DepreciacaoRepository depreciacaoRepository;
    private Depreciacao depreciacao = new Depreciacao();

    int patId;
    Date dataInicial;
    Date dataFinal;
    private List<Depreciacao> depreciacoes;

    public DepreciacaoBean() {

    }

    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.depreciacoes = depreciacaoRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar() {
        int id = depreciacao.getPatrimonioid().getId();
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.depreciacoes = depreciacaoRepository.Pesquisar(id, empresa.getId());
    }

    public void AbrirModal() {
        this.depreciacao = new Depreciacao();
        PrimeFaces.current().executeScript("$('#CadastrarDepreciacao').modal('show');");
    }

    public void Depreciar() {
        Date dataInical = getDataInicial();
        Date dataFinal = getDataFinal();
        if (dataInical == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Período Inicial!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarDepreciacao').modal('show');");
        } else if (dataFinal == null) {
            FacesMessage fm2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Período Final!");
            FacesContext context2 = FacesContext.getCurrentInstance();
            context2.addMessage(null, fm2);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarDepreciacao').modal('show');");
        } else {
            //String anoI = new SimpleDateFormat("yyyy").format(dataInical);
            //String mesI = new SimpleDateFormat("MM").format(dataInical);

            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.depreciacoes = depreciacaoRepository.ListarFaltaDepreciar(dataInical, dataFinal, empresa.getId());
    
            for (int i = 0; i < this.depreciacoes.size(); i++) {
                this.depreciacao = depreciacoes.get(i);
                this.depreciacao.setDepreciacao(1);
                this.depreciacao.setDatadepreciacao(new Date());
                depreciacaoRepository.save(this.depreciacao);
            }
    
            this.depreciacao = new Depreciacao();
            this.AtualizarTabela();
            setDataInicial(null);
            setDataFinal(null);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    
            FacesMessage fm3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Depreciado com Sucesso!");
            FacesContext context3 = FacesContext.getCurrentInstance();
            context3.addMessage(null, fm3);
        }
    }

    public List<Depreciacao> getDepreciacoes() {
        return depreciacoes;
    }

    public void setDepreciacoes(List<Depreciacao> depreciacoes) {
        this.depreciacoes = depreciacoes;
    }

    public DepreciacaoRepository getDepreciacaoRepository() {
        return depreciacaoRepository;
    }

    public void setDepreciacaoRepository(DepreciacaoRepository depreciacaoRepository) {
        this.depreciacaoRepository = depreciacaoRepository;
    }

    public Depreciacao getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(Depreciacao depreciacao) {
        this.depreciacao = depreciacao;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

}