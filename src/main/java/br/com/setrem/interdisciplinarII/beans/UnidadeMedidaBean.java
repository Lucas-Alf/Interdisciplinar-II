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
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.unidadeMedidas = unidadeMedidaRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.unidadeMedidas = unidadeMedidaRepository.Pesquisar(descricao, empresa.getId());
    }

    public void AbrirModal() {
        this.unidadeMedida = new UnidadeMedida();
        PrimeFaces.current().executeScript("$('#CadastrarUnidadeMedida').modal('show');");
    }

    public void Salvar() {
        if (this.unidadeMedida.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm); 
        } else if (this.unidadeMedida.getSigla().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Sigla.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            unidadeMedida.setCliForid(empresa);
            unidadeMedidaRepository.save(this.unidadeMedida);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarUnidadeMedida').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            unidadeMedidaRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            unidadeMedida = unidadeMedidaRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarUnidadeMedida').modal('show');");
        }
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