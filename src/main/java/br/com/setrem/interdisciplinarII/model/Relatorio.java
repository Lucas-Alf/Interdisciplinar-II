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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lukin
 */
@Entity(name = "relatorio")

public class Relatorio implements Serializable {

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
    @Column(name = "sqlquery")
    private String sqlquery;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relatorioid")
    private Collection<SolicitacaoRelatorio> SolicitacaoRelatorioCollection;
    @JoinColumn(name = "CliForid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relatorioid")
    private Collection<FiltroRelatorio> FiltroRelatorioCollection;

    public Relatorio() {
    }

    public Relatorio(Integer id) {
        this.id = id;
    }

    public Relatorio(Integer id, String nome, boolean excluido, String sqlquery) {
        this.id = id;
        this.nome = nome;
        this.excluido = excluido;
        this.sqlquery = sqlquery;
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

    public String getSqlquery() {
        return sqlquery;
    }

    public void setSqlquery(String sqlquery) {
        this.sqlquery = sqlquery;
    }

    @XmlTransient
    public Collection<SolicitacaoRelatorio> getSolicitacaoRelatorioCollection() {
        return SolicitacaoRelatorioCollection;
    }

    public void setSolicitacaoRelatorioCollection(Collection<SolicitacaoRelatorio> SolicitacaoRelatorioCollection) {
        this.SolicitacaoRelatorioCollection = SolicitacaoRelatorioCollection;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }

    @XmlTransient
    public Collection<FiltroRelatorio> getFiltroRelatorioCollection() {
        return FiltroRelatorioCollection;
    }

    public void setFiltroRelatorioCollection(Collection<FiltroRelatorio> FiltroRelatorioCollection) {
        this.FiltroRelatorioCollection = FiltroRelatorioCollection;
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
        if (!(object instanceof Relatorio)) {
            return false;
        }
        Relatorio other = (Relatorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Relatorio[ id=" + id + " ]";
    }
    
}
