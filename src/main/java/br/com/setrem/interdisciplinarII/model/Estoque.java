/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * 
 */
@Entity(name = "estoque")

public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "notafiscal")
    private String notafiscal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lancarpatrimonio")
    private int lancarpatrimonio;
    @JoinColumn(name = "fornecedorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor fornecedorid;
    @JoinColumn(name = "CliForid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;
    @JoinColumn(name = "localid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localid;
    @JoinColumn(name = "movimentacaoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movimentacao movimentacaoid;
    @JoinColumn(name = "precoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Preco precoid;
    @JoinColumn(name = "produtoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produtoid;

    public Estoque() {
    }

    public Estoque(Integer id) {
        this.id = id;
    }

    public Estoque(Integer id, BigDecimal quantidade, String notafiscal, int lancarpatrimonio) {
        this.id = id;
        this.quantidade = quantidade;
        this.notafiscal = notafiscal;
        this.lancarpatrimonio = lancarpatrimonio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getNotafiscal() {
        return notafiscal;
    }

    public void setNotafiscal(String notafiscal) {
        this.notafiscal = notafiscal;
    }

    public int getLancarpatrimonio() {
        return lancarpatrimonio;
    }

    public void setLancarpatrimonio(int lancarpatrimonio) {
        this.lancarpatrimonio = lancarpatrimonio;
    }

    public CliFor getFornecedorid() {
        return fornecedorid;
    }

    public void setFornecedorid(CliFor fornecedorid) {
        this.fornecedorid = fornecedorid;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }

    public Local getLocalid() {
        return localid;
    }

    public void setLocalid(Local localid) {
        this.localid = localid;
    }

    public Movimentacao getMovimentacaoid() {
        return movimentacaoid;
    }

    public void setMovimentacaoid(Movimentacao movimentacaoid) {
        this.movimentacaoid = movimentacaoid;
    }

    public Preco getPrecoid() {
        return precoid;
    }

    public void setPrecoid(Preco precoid) {
        this.precoid = precoid;
    }

    public Produto getProdutoid() {
        return produtoid;
    }

    public void setProdutoid(Produto produtoid) {
        this.produtoid = produtoid;
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
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Estoque[ id=" + id + " ]";
    }

}
