package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Historico;
import br.com.setrem.interdisciplinarII.repository.HistoricoRepository;

@Named(value = "historicoBean")
@SessionScoped
public class HistoricoBean implements Serializable {

    @Autowired
    private HistoricoRepository historicoRepository;
    private Historico historico = new Historico();

    private List<Historico> historicos;

    public HistoricoBean() {

    }

    public void AtualizarTabela() {
        this.historicos = historicoRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.historicos = historicoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.historico = new Historico();
        PrimeFaces.current().executeScript("$('#CadastrarHistorico').modal('show');");
    }

    public void Salvar() {
        if (this.historico.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            historicoRepository.save(this.historico);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            historicoRepository.deleteById(id);
            this.AtualizarTabela();

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
            historico = historicoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarHistorico').modal('show');");
        }
    }

    public void Alterar() {
        historicoRepository.save(historico);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public List<Historico> getLocais() {
        return historicos;
    }

}