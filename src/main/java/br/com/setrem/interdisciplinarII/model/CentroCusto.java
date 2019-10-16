/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.util.Collection;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * 
 */
@Entity(name = "CentroCusto")

public class CentroCusto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "CliForid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CentroCustoid")
    private Collection<LancamentoContabil> LancamentoContabilCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CentroCustoid")
    private Collection<Patrimonio> patrimonioCollection;

    public CentroCusto() {
    }

    public CentroCusto(Integer id) {
        this.id = id;
    }

    public CentroCusto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }

    @XmlTransient
    public Collection<LancamentoContabil> getLancamentoContabilCollection() {
        return LancamentoContabilCollection;
    }

    public void setLancamentoContabilCollection(Collection<LancamentoContabil> LancamentoContabilCollection) {
        this.LancamentoContabilCollection = LancamentoContabilCollection;
    }

    @XmlTransient
    public Collection<Patrimonio> getPatrimonioCollection() {
        return patrimonioCollection;
    }

    public void setPatrimonioCollection(Collection<Patrimonio> patrimonioCollection) {
        this.patrimonioCollection = patrimonioCollection;
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
        if (!(object instanceof CentroCusto)) {
            return false;
        }
        CentroCusto other = (CentroCusto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.CentroCusto[ id=" + id + " ]";
    }

}
