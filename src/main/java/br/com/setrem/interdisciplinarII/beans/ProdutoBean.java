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
import br.com.setrem.interdisciplinarII.model.Grupo;
import br.com.setrem.interdisciplinarII.model.Marca;
import br.com.setrem.interdisciplinarII.model.Produto;
import br.com.setrem.interdisciplinarII.model.UnidadeMedida;
import br.com.setrem.interdisciplinarII.repository.ProdutoRepository;

@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    @Autowired
    private ProdutoRepository produtoRepository;
    private Produto produto = new Produto();
    private Marca marca = new Marca();
    private Grupo grupo = new Grupo();
    private UnidadeMedida unidadeMedida = new UnidadeMedida();
    private CliFor cliFor = new CliFor();

    private List<Produto> produtos;

    public ProdutoBean() {

    }

    public void AtualizarTabela() {
        this.produtos = produtoRepository.findAll();
    }

    public void Pesquisar(String descricao) {
        this.produtos = produtoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.produto = new Produto();
        PrimeFaces.current().executeScript("$('#CadastrarProduto').modal('show');");
    }

    public void Salvar() {
        if (this.produto.getNome().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else if (this.produto.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            produtoRepository.save(this.produto);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            produtoRepository.deleteById(id);
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
            produto = produtoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarProduto').modal('show');");
        }
    }

    public void Alterar() {
        produtoRepository.save(produto);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = produtoRepository.findAll();
        }
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return ProdutoRepository return the produtoRepository
     */
    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }

    /**
     * @param produtoRepository the produtoRepository to set
     */
    public void setProdutoRepository(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public CliFor getCliFor() {
        return cliFor;
    }

    public void setCliFor(CliFor cliFor) {
        this.cliFor = cliFor;
    }

    /*public List<CliFor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<CliFor> fornecedores) {
        this.fornecedores = fornecedores;
    }*/

}