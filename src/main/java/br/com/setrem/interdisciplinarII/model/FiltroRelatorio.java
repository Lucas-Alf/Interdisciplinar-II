/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;

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
@Entity(name = "filtrorelatorio")

public class FiltroRelatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "excluido")
    private boolean excluido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sqlwhere")
    private String sqlwhere;
    @JoinColumn(name = "relatorioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Relatorio relatorioid;

    public FiltroRelatorio() {
    }

    public FiltroRelatorio(Integer id) {
        this.id = id;
    }

    public FiltroRelatorio(Integer id, String nome, boolean excluido, String sqlwhere) {
        this.id = id;
        this.nome = nome;
        this.excluido = excluido;
        this.sqlwhere = sqlwhere;
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

    public boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public String getSqlwhere() {
        return sqlwhere;
    }

    public void setSqlwhere(String sqlwhere) {
        this.sqlwhere = sqlwhere;
    }

    public Relatorio getRelatorioid() {
        return relatorioid;
    }

    public void setRelatorioid(Relatorio relatorioid) {
        this.relatorioid = relatorioid;
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
        if (!(object instanceof FiltroRelatorio)) {
            return false;
        }
        FiltroRelatorio other = (FiltroRelatorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.FiltroRelatorio[ id=" + id + " ]";
    }

}
