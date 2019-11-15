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

@Entity(name = "patrimonio")

public class Patrimonio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dataaquisicao")
    @Temporal(TemporalType.DATE)
    private Date dataaquisicao;
    @Column(name = "valor")
    private double valor;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "baixado")
    private int baixado;
    @Column(name = "depreciavel")
    private boolean depreciavel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patrimonioid")
    private Collection<BaixaBem> baixabemCollection;
    @JoinColumn(name = "centrocustoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CentroCusto CentroCustoid;
    @JoinColumn(name = "fornecedorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor fornecedorid;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;
    @JoinColumn(name = "estadoconservacaoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoConservacao EstadoConservacaoid;
    @JoinColumn(name = "grupobemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GrupoBem GrupoBemid;
    @JoinColumn(name = "produtoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produtoid;

    public Patrimonio() {
    }

    public Patrimonio(Integer id) {
        this.id = id;
    }

    public Patrimonio(Integer id, Date dataaquisicao, double valor, String observacao) {
        this.id = id;
        this.dataaquisicao = dataaquisicao;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataaquisicao() {
        return dataaquisicao;
    }

    public void setDataaquisicao(Date dataaquisicao) {
        this.dataaquisicao = dataaquisicao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public Collection<BaixaBem> getBaixabemCollection() {
        return baixabemCollection;
    }

    public void setBaixabemCollection(Collection<BaixaBem> baixabemCollection) {
        this.baixabemCollection = baixabemCollection;
    }

    public CentroCusto getCentroCustoid() {
        return CentroCustoid;
    }

    public void setCentroCustoid(CentroCusto CentroCustoid) {
        this.CentroCustoid = CentroCustoid;
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
        if (!(object instanceof Patrimonio)) {
            return false;
        }
        Patrimonio other = (Patrimonio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Patrimonio[ id=" + id + " ]";
    }

    public GrupoBem getGrupoBemid() {
        return GrupoBemid;
    }

    public void setGrupoBemid(GrupoBem grupoBemid) {
        GrupoBemid = grupoBemid;
    }

    public int getBaixado() {
        return baixado;
    }

    public void setBaixado(int baixado) {
        this.baixado = baixado;
    }

    public boolean isDepreciavel() {
        return depreciavel;
    }

    public void setDepreciavel(boolean depreciavel) {
        this.depreciavel = depreciavel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public EstadoConservacao getEstadoConservacaoid() {
        return EstadoConservacaoid;
    }

    public void setEstadoConservacaoid(EstadoConservacao estadoConservacaoid) {
        EstadoConservacaoid = estadoConservacaoid;
    }

}