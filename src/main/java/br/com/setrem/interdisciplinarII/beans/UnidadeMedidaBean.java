package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.UnidadeMedida;
import br.com.setrem.interdisciplinarII.repository.UnidadeMedidaRepository;

@Named(value = "unidadeMedidaBean")
@SessionScoped
public class UnidadeMedidaBean implements Serializable {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;
    private UnidadeMedida unidadeMedida = new UnidadeMedida();

    private int id;
    private String descricao;
    private List<UnidadeMedida> unidadeMedidas;

    public UnidadeMedidaBean() {

    }

    public void AtualizarTabela() {
        this.unidadeMedidas = unidadeMedidaRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.unidadeMedidas = unidadeMedidaRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.unidadeMedida = new UnidadeMedida();
        PrimeFaces.current().executeScript("$('#CadastrarUnidadeMedida').modal('show');");
    }

    public void Salvar() {
        if (this.unidadeMedida.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm); 
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        } else {
            unidadeMedidaRepository.save(this.unidadeMedida);
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
            unidadeMedidaRepository.deleteById(id);
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
            unidadeMedida = unidadeMedidaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarUnidadeMedida').modal('show');");
        }
    }

    public void Alterar() {
        unidadeMedidaRepository.save(unidadeMedida);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
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

    public List<UnidadeMedida> getUnidadeMedidas() {
        if (this.unidadeMedidas == null) {
            this.unidadeMedidas = unidadeMedidaRepository.findAll();
        }
        return unidadeMedidas;
    }

    public void setUnidadeMedidas(List<UnidadeMedida> unidadeMedidas) {
        this.unidadeMedidas = unidadeMedidas;
    }

}