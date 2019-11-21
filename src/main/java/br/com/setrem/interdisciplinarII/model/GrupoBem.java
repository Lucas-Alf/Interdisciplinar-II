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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "grupobem")

public class GrupoBem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "taxadepreciacao")
    private double taxadepreciacao;
    @Column(name = "vidautil")
    private double vidautil;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;
    
    public GrupoBem() {
    }

    public GrupoBem(Integer id) {
        this.id = id;
    }

    public GrupoBem(Integer id, String descricao, double taxadepreciacao, double vidautil) {
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

    public double getTaxadepreciacao() {
        return taxadepreciacao;
    }

    public void setTaxadepreciacao(double taxadepreciacao) {
        this.taxadepreciacao = taxadepreciacao;
    }

    public double getVidautil() {
        return vidautil;
    }
    
    public void setVidautil(double vidautil) {
        this.vidautil = vidautil;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }

}