/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * 
 */
@Entity(name = "lancamentocontabil")

public class LancamentoContabil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    //@NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @Basic(optional = false)
    //@NotNull
    @Column(name = "datahora")
    @Temporal(TemporalType.DATE)
    private Date datahora;

    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 150)
    @Column(name = "historico")
    private String historico;

    @Basic(optional = false)
    //@NotNull
    @Column(name = "tipo")
    private String tipo;

    @JoinColumn(name = "idconta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conta idconta;

    @JoinColumn(name = "centrocustoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CentroCusto centrocustoid;
    
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor cliforid;

    public LancamentoContabil() {
    }

    public LancamentoContabil(Integer id) {
        this.id = id;
    }

    public LancamentoContabil(Integer id, BigDecimal valor, Date datahora, String historico, String tipo, Conta conta) {
        this.id = id;
        this.valor = valor;
        this.datahora = datahora;
        this.historico = historico;
        this.tipo = tipo;
        this.idconta = conta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public CentroCusto getCentrocustoid() {
        return centrocustoid;
    }

    public void setCentrocustoid(CentroCusto centrocustoid) {
        this.centrocustoid = centrocustoid;
    }

    public Conta getIdconta() {
        return idconta;
    }

    public void setIdconta(Conta idconta) {
        this.idconta = idconta;
    }

    public CliFor getCliforid() {
        return cliforid;
    }

    public void setCliforid(CliFor cliforid) {
        this.cliforid = cliforid;
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
        if (!(object instanceof LancamentoContabil)) {
            return false;
        }
        LancamentoContabil other = (LancamentoContabil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.LancamentoContabil[ id=" + id + " ]";
    }

}
