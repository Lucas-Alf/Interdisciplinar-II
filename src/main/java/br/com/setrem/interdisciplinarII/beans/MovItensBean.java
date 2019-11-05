package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.MovItens;
import br.com.setrem.interdisciplinarII.repository.MovItensRepository;

@Named(value = "movItenBean")
@SessionScoped
public class MovItensBean implements Serializable {

    @Autowired
    private MovItensRepository movItensRepository;
    private MovItens movItens = new MovItens();

    private int id;
    private List<MovItens> movItenss;

    public MovItensBean() {

    }

    public void AtualizarTabela() {
        this.movItenss = movItensRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.movItenss = movItensRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.movItens = new MovItens();
        PrimeFaces.current().executeScript("$('#CadastrarMovItens').modal('show');");
    }

    public void Salvar() {
        /*if (this.movimentacao.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {*/
            movItensRepository.save(this.movItens);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        //}
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            movItensRepository.deleteById(id);
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
            movItens = movItensRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarMovItens').modal('show');");
        }
    }

    public void Alterar() {
        movItensRepository.save(movItens);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public MovItens getMovItens() {
        return movItens;
    }

    public void setMovItens(MovItens movItens) {
        this.movItens = movItens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<MovItens> getMovimentacoes() {
        if (this.movItenss == null) {
            this.movItenss = movItensRepository.findAll();
        }
        return movItenss;
    }

    public void setMovItenss(List<MovItens> movItenss) {
        this.movItenss = movItenss;
    }

}