package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "pagamento")

public class Pagamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "valorpago")
    private  double valorpago;
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "contapagarid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContaPagar ContaPagarid;
    @JoinColumn(name = "especieid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Especie Especieid;
    @JoinColumn(name = "contacorrenteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContaCorrente ContaCorrenteid;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

public Pagamento() {
    }

    public Pagamento(Integer id) {
        this.id = id;
    }

    public Pagamento(Integer id, double valorpago, Date datapagamento, String descricao) {
        this.id = id;
        this.valorpago = valorpago;
        this.datapagamento = datapagamento;
        this.descricao = descricao;
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContaPagar getContaPagarid() {
        return ContaPagarid;
    }

    public void setContaPagarid(ContaPagar ContaPagarid) {
        this.ContaPagarid = ContaPagarid;
    }

    public Especie getEspecieid() {
        return Especieid;
    }

    public void setEspecieid(Especie Especieid) {
        this.Especieid = Especieid;
    }

    public ContaCorrente getContaCorrenteid() {
        return ContaCorrenteid;
    }

    public void setContaCorrenteid(ContaCorrente ContaCorrenteid) {
        this.ContaCorrenteid = ContaCorrenteid;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }
}
