/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * 
 */
@Entity(name = "grupobem")

public class GrupoBem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 100)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    //@NotNull
    @Column(name = "taxadepreciacao")
    private BigDecimal taxadepreciacao;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "vidautil")
    private BigDecimal vidautil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "GrupoBemid")
    private Collection<Patrimonio> patrimonioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "GrupoBemid")
    private Collection<ReavaliacaoBem> ReavaliacaoBemCollection;

    public GrupoBem() {
    }

    public GrupoBem(Integer id) {
        this.id = id;
    }

    public GrupoBem(Integer id, String descricao, BigDecimal taxadepreciacao, BigDecimal vidautil) {
        this.id = id;
        this.descricao = descricao;
        this.taxadepreciacao = taxadepreciacao;
        this.vidautil = vidautil;
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

    public BigDecimal getTaxadepreciacao() {
        return taxadepreciacao;
    }

    public void setTaxadepreciacao(BigDecimal taxadepreciacao) {
        this.taxadepreciacao = taxadepreciacao;
    }

    public BigDecimal getVidautil() {
        return vidautil;
    }

    public void setVidautil(BigDecimal vidautil) {
        this.vidautil = vidautil;
    }

    @XmlTransient
    public Collection<Patrimonio> getPatrimonioCollection() {
        return patrimonioCollection;
    }

    public void setPatrimonioCollection(Collection<Patrimonio> patrimonioCollection) {
        this.patrimonioCollection = patrimonioCollection;
    }

    @XmlTransient
    public Collection<ReavaliacaoBem> getReavaliacaoBemCollection() {
        return ReavaliacaoBemCollection;
    }

    public void setReavaliacaoBemCollection(Collection<ReavaliacaoBem> ReavaliacaoBemCollection) {
        this.ReavaliacaoBemCollection = ReavaliacaoBemCollection;
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
        if (!(object instanceof GrupoBem)) {
            return false;
        }
        GrupoBem other = (GrupoBem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.GrupoBem[ id=" + id + " ]";
    }

}
