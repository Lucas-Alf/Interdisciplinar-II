package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.MotivoBaixa;
import br.com.setrem.interdisciplinarII.repository.MotivoBaixaRepository;

@Named(value = "motivoBaixaBean")
@SessionScoped
public class MotivoBaixaBean implements Serializable {

    @Autowired
    private MotivoBaixaRepository motivoBaixaRepository;
    private MotivoBaixa motivoBaixa = new MotivoBaixa();

    private int id;
    private String descricao;
    private List<MotivoBaixa> motivoBaixas;

    public MotivoBaixaBean() {

    }

    public void AtualizarTabela() {
        this.motivoBaixas = motivoBaixaRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.motivoBaixas = motivoBaixaRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.motivoBaixa = new MotivoBaixa();
        PrimeFaces.current().executeScript("$('#CadastrarMotivoBaixa').modal('show');");
    }

    public void Salvar() {
        if (this.motivoBaixa.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            motivoBaixaRepository.save(this.motivoBaixa);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            motivoBaixaRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            motivoBaixa = motivoBaixaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarMotivoBaixa').modal('show');");
        }
    }

    public void Alterar() {
        motivoBaixaRepository.save(motivoBaixa);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public MotivoBaixa getMotivoBaixa() {
        return motivoBaixa;
    }

    public void setMotivoBaixa(MotivoBaixa motivoBaixa) {
        this.motivoBaixa = motivoBaixa;
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

    public List<MotivoBaixa> getMotivoBaixas() {
        if (this.motivoBaixas == null) {
            this.motivoBaixas = motivoBaixaRepository.findAll();
        }
        return motivoBaixas;
    }

    public void setMotivoBaixas(List<MotivoBaixa> motivoBaixas) {
        this.motivoBaixas = motivoBaixas;
    }

}