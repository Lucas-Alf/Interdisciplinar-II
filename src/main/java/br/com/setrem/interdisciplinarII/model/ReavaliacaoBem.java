/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * 
 */
@Entity(name = "reavaliacaobem")

public class ReavaliacaoBem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorreavaliado")
    private BigDecimal valorreavaliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valordepreciar")
    private BigDecimal valordepreciar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "residual")
    private BigDecimal residual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorresidual")
    private BigDecimal valorresidual;
    @JoinColumn(name = "grupobemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GrupoBem GrupoBemid;
    @JoinColumn(name = "patrimonioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patrimonio patrimonioid;

    public ReavaliacaoBem() {
    }

    public ReavaliacaoBem(Integer id) {
        this.id = id;
    }

    public ReavaliacaoBem(Integer id, Date data, BigDecimal valorreavaliado, BigDecimal valordepreciar,
            BigDecimal residual, BigDecimal valorresidual) {
        this.id = id;
        this.data = data;
        this.valorreavaliado = valorreavaliado;
        this.valordepreciar = valordepreciar;
        this.residual = residual;
        this.valorresidual = valorresidual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorreavaliado() {
        return valorreavaliado;
    }

    public void setValorreavaliado(BigDecimal valorreavaliado) {
        this.valorreavaliado = valorreavaliado;
    }

    public BigDecimal getValordepreciar() {
        return valordepreciar;
    }

    public void setValordepreciar(BigDecimal valordepreciar) {
        this.valordepreciar = valordepreciar;
    }

    public BigDecimal getResidual() {
        return residual;
    }

    public void setResidual(BigDecimal residual) {
        this.residual = residual;
    }

    public BigDecimal getValorresidual() {
        return valorresidual;
    }

    public void setValorresidual(BigDecimal valorresidual) {
        this.valorresidual = valorresidual;
    }

    public GrupoBem getGrupoBemid() {
        return GrupoBemid;
    }

    public void setGrupoBemid(GrupoBem GrupoBemid) {
        this.GrupoBemid = GrupoBemid;
    }

    public Patrimonio getPatrimonioid() {
        return patrimonioid;
    }

    public void setPatrimonioid(Patrimonio patrimonioid) {
        this.patrimonioid = patrimonioid;
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
        if (!(object instanceof ReavaliacaoBem)) {
            return false;
        }
        ReavaliacaoBem other = (ReavaliacaoBem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.ReavaliacaoBem[ id=" + id + " ]";
    }

}
