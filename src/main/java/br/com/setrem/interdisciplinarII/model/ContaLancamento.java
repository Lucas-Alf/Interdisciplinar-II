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
@Entity(name = "contalancamento")

public class ContaLancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "contaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conta contaid;
    @JoinColumn(name = "lancamentoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LancamentoContabil lancamentoid;

    public ContaLancamento() {
    }

    public ContaLancamento(Integer id) {
        this.id = id;
    }

    public ContaLancamento(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Conta getContaid() {
        return contaid;
    }

    public void setContaid(Conta contaid) {
        this.contaid = contaid;
    }

    public LancamentoContabil getLancamentoid() {
        return lancamentoid;
    }

    public void setLancamentoid(LancamentoContabil lancamentoid) {
        this.lancamentoid = lancamentoid;
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
        if (!(object instanceof ContaLancamento)) {
            return false;
        }
        ContaLancamento other = (ContaLancamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.ContaLancamento[ id=" + id + " ]";
    }

}
