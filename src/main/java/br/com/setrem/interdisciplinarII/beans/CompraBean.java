package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.print.DocFlavor.STRING;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
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

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    private Movimentacao movimentacao = new Movimentacao();

    private List<MovItens> movItenss;
    private List<MovItens> produtos;

    String produtoid;
    String localid;
    double valor;
    double qtde;
    int seq = 0;
 
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
        PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
    }

    /*public void SalvarMovimentacao() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        movimentacao.setEmpresaId(empresa);
        movimentacao.setTipo('C');
        movimentacao.setValortotal(0.0);
        movimentacao.setId(movimentacaoRepository.maxId());
        movimentacaoRepository.save(this.movimentacao);
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
    }*/

    public void SalvarEstoque() {
        if( this.produtos == null){
            this.produtos = new ArrayList<MovItens>();
        }

        if (getSeq() == 0) {
            setSeq(1);
        } else {
            setSeq(getSeq() + 1);
        }

        movItens.setSequencia(getSeq());
        this.produtos.add(movItens);
        movItens = new MovItens();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
    }
    
    public void DeletarEstoque(int sequencia){
        for (int i = 0; i < this.produtos.size(); i++) {
            if (produtos.get(i).getSequencia() == sequencia) {
                this.movItens = produtos.get(i);
            }
        }
        this.produtos.remove(movItens);
        movItens = new MovItens();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
    }

    public void SalvarMovimentacao() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        movimentacao.setEmpresaId(empresa);
        movimentacao.setTipo('C');
        movimentacao.setValortotal(0.0);
        movimentacao.setId(movimentacaoRepository.maxId());
        movimentacaoRepository.save(this.movimentacao);

        //PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        //PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
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
            PrimeFaces.current().executeScript("$('#CadastrarCompra').modal('show');");
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

    public List<MovItens> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<MovItens> produtos) {
        this.produtos = produtos;
    }



    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    public String getProdutoid() {
        return produtoid;
    }

    public void setProdutoid(String produtoid) {
        this.produtoid = produtoid;
    }

	public String getLocalid() {
		return localid;
	}

	public void setLocalid(String localid) {
		this.localid = localid;
	}

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}