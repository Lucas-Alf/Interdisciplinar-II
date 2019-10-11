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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lukin
 */

 
@Entity(name = "auditoria")
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tabela")
    private String tabela;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "valorantigo")
    private Object valorantigo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "valornovo")
    private Object valornovo;

    public Auditoria() {
    }

    public Auditoria(Integer id) {
        this.id = id;
    }

    public Auditoria(Integer id, String tabela, Object valorantigo, Object valornovo) {
        this.id = id;
        this.tabela = tabela;
        this.valorantigo = valorantigo;
        this.valornovo = valornovo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public Object getValorantigo() {
        return valorantigo;
    }

    public void setValorantigo(Object valorantigo) {
        this.valorantigo = valorantigo;
    }

    public Object getValornovo() {
        return valornovo;
    }

    public void setValornovo(Object valornovo) {
        this.valornovo = valornovo;
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
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Auditoria[ id=" + id + " ]";
    }
    
}
