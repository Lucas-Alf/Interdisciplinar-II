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
import br.com.setrem.interdisciplinarII.model.Movimentacao;
import br.com.setrem.interdisciplinarII.repository.MovItensRepository;
import br.com.setrem.interdisciplinarII.repository.MovimentacaoRepository;

@Named(value = "compraBean")
@SessionScoped
public class CompraBean implements Serializable {

    @Autowired
    private MovItensRepository movItensRepository;
    private MovItens movItens = new MovItens();

    private MovimentacaoRepository movimentacaoRepository;
    private Movimentacao movimentacao = new Movimentacao();

    private List<MovItens> movItenss;

    public CompraBean() {

    }

    public void AtualizarTabela() {
        this.movItenss = movItensRepository.findAll();
    }

    /*
     * public void Pesquisar(String descricao) { this.grupoBens =
     * grupoBemRepository.pesquisar(descricao); }
     */

    public void AbrirModal() {
        this.movItens = new MovItens();
        this.movimentacao = new Movimentacao();
        PrimeFaces.current().executeScript("$('#CadastrarGrupoBem').modal('show');");
    }

    public void Salvar() {

        // grupoBemRepository.save(this.grupoBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);

    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            // grupoBemRepository.deleteById(id);
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
            // grupoBem = grupoBemRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarGrupoBem').modal('show');");
        }
    }

    public void Alterar() {
        // grupoBemRepository.save(grupoBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    /**
     * @return MovItensRepository return the movItensRepository
     */
    public MovItensRepository getMovItensRepository() {
        return movItensRepository;
    }

    /**
     * @param movItensRepository the movItensRepository to set
     */
    public void setMovItensRepository(MovItensRepository movItensRepository) {
        this.movItensRepository = movItensRepository;
    }

    /**
     * @return MovItens return the movItens
     */
    public MovItens getMovItens() {
        return movItens;
    }

    /**
     * @param movItens the movItens to set
     */
    public void setMovItens(MovItens movItens) {
        this.movItens = movItens;
    }

    /**
     * @return MovimentacaoRepository return the movimentacaoRepository
     */
    public MovimentacaoRepository getMovimentacaoRepository() {
        return movimentacaoRepository;
    }

    /**
     * @param movimentacaoRepository the movimentacaoRepository to set
     */
    public void setMovimentacaoRepository(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    /**
     * @return Movimentacao return the movimentacao
     */
    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    /**
     * @param movimentacao the movimentacao to set
     */
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     * @return List<MovItens> return the movItenss
     */
    public List<MovItens> getMovItenss() {
        return movItenss;
    }

    /**
     * @param movItenss the movItenss to set
     */
    public void setMovItenss(List<MovItens> movItenss) {
        this.movItenss = movItenss;
    }

}