package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Produto;
import br.com.setrem.interdisciplinarII.repository.ProdutoRepository;

@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    @Autowired
    private ProdutoRepository produtoRepository;
    private Produto produto = new Produto();

    private int id;
    private String descricao;
    private String nome;
    private double quantidademinima;
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
        if (this.produto.getDescricao().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            produtoRepository.save(this.produto);
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
            produtoRepository.deleteById(id);
            this.AtualizarTabela();
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

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return double return the quantidademinima
     */
    public double getQuantidademinima() {
        return quantidademinima;
    }

    /**
     * @param quantidademinima the quantidademinima to set
     */
    public void setQuantidademinima(double quantidademinima) {
        this.quantidademinima = quantidademinima;
    }

}