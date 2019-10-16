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
@Entity(name = "fechaestoque")

public class FechaEstoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Basic(optional = false)
    @NotNull
    @Column(name = "customedio")
    private BigDecimal customedio;

    @JoinColumn(name = "idproduto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto idproduto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int qtde;

    public FechaEstoque() {
    }

    public FechaEstoque(Date data) {
        this.data = data;
    }

    public FechaEstoque(Date data, BigDecimal customedio, int qtde) {
        this.data = data;
        this.customedio = customedio;
        this.qtde = qtde;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (data != null ? data.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaEstoque)) {
            return false;
        }
        FechaEstoque other = (FechaEstoque) object;
        if ((this.data == null && other.data != null) || (this.data != null && !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.FechaEstoque[ data=" + data + " ]";
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getCustomedio() {
        return customedio;
    }

    public void setCustomedio(BigDecimal customedio) {
        this.customedio = customedio;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    

}
