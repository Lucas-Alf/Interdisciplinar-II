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

@Entity(name = "contapagar")

public class ContaPagar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    private  String descricao;
    @Column(name = "numdocumento")
    private  double numdocumento;
    @Column(name = "seriedocumento")
    private  double seriedocumento;
    @Column(name = "datadocumento")
    @Temporal(TemporalType.DATE)
    private Date datadocumento;
    @Column(name = "datavencimento")
    @Temporal(TemporalType.DATE)
    private Date datavencimento;
    @Column(name = "datapagamento")
    @Temporal(TemporalType.DATE)
    private Date datapagamento;
    @Column(name = "valor")
    private  double valor;
    @Column(name = "saldo")
    private  double saldo;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;
    @JoinColumn(name = "fornecedorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor fornecedorid;

    public ContaPagar() {
    }

    public ContaPagar(Integer id) {
        this.id = id;
    }

    public ContaPagar(Integer id, String descricao, double numdocumento,double seriedocumento, Date datadocumento, Date datavencimento, Date datapagamento, double valor, double saldo) {
        this.id = id;
        this.descricao = descricao;
        this.numdocumento = numdocumento;
        this.seriedocumento = seriedocumento;
        this.datadocumento = datadocumento;
        this.datavencimento = datavencimento;
        this.datapagamento = datapagamento;
        this.valor = valor;
        this.saldo = saldo;
        
        

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(double numdocumento) {
        this.numdocumento = numdocumento;
    }

    public double getSeriedocumento() {
        return seriedocumento;
    }

    public void setSeriedocumento(double seriedocumento) {
        this.seriedocumento = seriedocumento;
    }

    public Date getDatadocumento() {
        return datadocumento;
    }

    public void setDatadocumento(Date datadocumento) {
        this.datadocumento = datadocumento;
    }

    public Date getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }

    public CliFor getFornecedorid() {
        return fornecedorid;
    }

    public void setFornecedorid(CliFor fornecedorid) {
        this.fornecedorid = fornecedorid;
    }
}