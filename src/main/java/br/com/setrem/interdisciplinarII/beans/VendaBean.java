package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.MovItens;
import br.com.setrem.interdisciplinarII.model.Movimentacao;
import br.com.setrem.interdisciplinarII.model.Saldo;
import br.com.setrem.interdisciplinarII.repository.MovItensRepository;
import br.com.setrem.interdisciplinarII.repository.MovimentacaoRepository;
import br.com.setrem.interdisciplinarII.repository.SaldoRepository;

@Named(value = "vendaBean")
@SessionScoped
public class VendaBean implements Serializable {

    @Autowired
    private MovItensRepository movItensRepository;
    private MovItens movItens = new MovItens();

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    private Movimentacao movimentacao = new Movimentacao();

    @Autowired
    private SaldoRepository saldoRepository;
    private Saldo saldo = new Saldo();

    private List<Movimentacao> movimentacoes;
    private List<MovItens> movItenss;
    private List<MovItens> produtos;
    private List<Saldo> saldos;

    String produtoid;
    String localid;
    double valor;
    double qtde;
    int seq = 0;
 
    public VendaBean() {

    }

    public void AtualizarTabelaSaida() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.movimentacoes = movimentacaoRepository.AtualizarTabelaSaida  (empresa.getId());
    }
    
    public void AtualizarTabelaMovItens() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.movItenss = movItensRepository.AtualizarTabela(empresa.getId());
    }

    public void ListarProdutos(int id) {
        this.movItenss = movItensRepository.ListarProdutos(id);
    }

    public void rowSelected(SelectEvent event) {
        Movimentacao mov = (Movimentacao) event.getObject();
        this.movItenss = movItensRepository.ListarProdutos(mov.getId());
   }

    public void AbrirModal() {
        this.movItens = new MovItens();
        this.movimentacao = new Movimentacao();
        PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
    }

    public void SalvarEstoque() {
        if (movItens.getProdutoId() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Produto.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (movItens.getLocalId() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Local.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (movItens.getQtde() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Quantidade.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (movItens.getValor() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else {
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
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        }
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
        PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
    }

    public void SalvarMovimentacao() {
        if (movimentacao.getData() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Data.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (movimentacao.getNotafiscal() == "") {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Nota Fiscal.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (movimentacao.getCliForid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Fornecedor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else if (produtos == null || produtos.size() == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Inclua ao menos 1 produto.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao3", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            movimentacao.setTipo('V');
            movimentacao.setValortotal(0);
            movimentacao.setEmpresaId(empresa);
            movimentacaoRepository.save(movimentacao);

            double valorTotal = 0;
            for (int i = 0; i < produtos.size(); i++) {
                movItens = produtos.get(i);
                movItens.setCliForId(empresa);
                movItens.setMovimentacaoId(movimentacao);
                movItensRepository.save(movItens);
                valorTotal += movItens.getValor();

                saldos = saldoRepository.BuscarSaldo(movItens.getProdutoId().getId());
                if (saldos.size() > 0) {
                    saldo.setId(saldos.get(0).getId());
                    saldo.setProdutoid(saldos.get(0).getProdutoid());
                    saldo.setQtde(saldos.get(0).getQtde() + movItens.getQtde());
                    saldo.setValor(saldos.get(0).getValor() + movItens.getValor());
                    saldoRepository.save(saldo);
                    saldo = new Saldo();
                    saldos.removeAll(saldos);
                } else {
                    saldo.setProdutoid(movItens.getProdutoId());
                    saldo.setQtde(movItens.getQtde());
                    saldo.setValor(movItens.getValor());
                    saldoRepository.save(saldo);
                    saldo = new Saldo();
                    saldos.removeAll(saldos);
                }
            }

            movimentacao.setValortotal(valorTotal);
            movimentacaoRepository.save(movimentacao);
            movimentacao = new Movimentacao();
            movItens = new MovItens();
            produtos.removeAll(produtos);
            this.AtualizarTabelaSaida();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            this.AtualizarTabelaSaida();

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
            PrimeFaces.current().executeScript("$('#CadastrarVenda').modal('show');");
        }
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

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public SaldoRepository getSaldoRepository() {
        return saldoRepository;
    }

    public void setSaldoRepository(SaldoRepository saldoRepository) {
        this.saldoRepository = saldoRepository;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
    }

    public List<Saldo> getSaldos() {
        return saldos;
    }

    public void setSaldos(List<Saldo> saldos) {
        this.saldos = saldos;
    }

}