
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "contaPagar")
public class ContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "numdocumento")
    private double numdocumento;
    @Column(name = "seriedocumento")
    private double seriedocumento;
    @Column(name = "datadocumento")
    private Date datadocumento;
    @Column(name = "datavencimento")
    private Date datavencimento;
    @Column(name = "datapagamento")
    private Date datapagamento;
    @Column(name = "valor")
    private double valor;
    @Column(name = "saldo")
    private double saldo;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

    public ContaPagar() {
    }

    public ContaPagar(Integer id) {
        this.id = id;
    }

    public ContaPagar(Integer id, String descricao, double NumDocumento, double SerieDocumento, Date DataDocumento,
            Date DataVencimento, Date DataPagamento, double Valor, double Saldo) {
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

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaPagar)) {
            return false;
        }
        ContaPagar other = (ContaPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.ContaPagar[ id=" + id + " ]";
    }

}