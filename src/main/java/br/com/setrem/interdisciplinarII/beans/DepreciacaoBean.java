package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

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
        this.depreciacoes = depreciacaoRepository.AtualizarTabela();
    }

    public void Pesquisar() {
        int id = depreciacao.getPatrimonioid().getId();
        this.depreciacoes = depreciacaoRepository.Pesquisar(id);
    }

    public void AbrirModal() {
        this.depreciacao = new Depreciacao();
        PrimeFaces.current().executeScript("$('#CadastrarDepreciacao').modal('show');");
    }

    public void Depreciar() {
        Date dataInical = getDataInicial();
        String anoI = new SimpleDateFormat("yyyy").format(dataInical);
        String mesI = new SimpleDateFormat("MM").format(dataInical);

        Date dataFinal = getDataFinal();
        String anoF = new SimpleDateFormat("yyyy").format(dataFinal);
        String mesF = new SimpleDateFormat("MM").format(dataFinal);

        //int id = getPatId();

        int id = depreciacao.getPatrimonioid().getId();
        this.depreciacoes = depreciacaoRepository.ListarFaltaDepreciar(id, Integer.parseInt(mesI), Integer.parseInt(mesF), Integer.parseInt(anoI), Integer.parseInt(anoF));



        //depreciacaoRepository.save(this.depreciacao);
        //this.AtualizarTabela();
        //PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Depreciado com Sucesso!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
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