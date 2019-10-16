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

/**
 *
 * 
 */
@Entity(name = "movitem")

public class MovItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "MovimentacaoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movimentacao MovimentacaoId;

    @JoinColumn(name = "ProdutoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movimentacao ProdutoId;

    @JoinColumn(name = "LocalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movimentacao LocalId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int qtde;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @JoinColumn(name = "CliForId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movimentacao CliForId;

    public MovItens() {
    }

    public MovItens(Integer id) {
        this.id = id;
    }

    public MovItens(Integer id, int qtde, BigDecimal valor) {
        this.id = id;
        this.qtde = qtde;
        this.valor = valor;
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
        if (!(object instanceof MovItens)) {
            return false;
        }
        MovItens other = (MovItens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.MovItem[ id=" + id + " ]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movimentacao getMovimentacaoId() {
        return MovimentacaoId;
    }

    public void setMovimentacaoId(Movimentacao movimentacaoId) {
        MovimentacaoId = movimentacaoId;
    }

    public Movimentacao getProdutoId() {
        return ProdutoId;
    }

    public void setProdutoId(Movimentacao produtoId) {
        ProdutoId = produtoId;
    }

    public Movimentacao getLocalId() {
        return LocalId;
    }

    public void setLocalId(Movimentacao localId) {
        LocalId = localId;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Movimentacao getCliForId() {
        return CliForId;
    }

    public void setCliForId(Movimentacao cliForId) {
        CliForId = cliForId;
    }

    
}
