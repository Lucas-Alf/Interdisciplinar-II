/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

@Entity(name = "despesainvestimento")
public class DespesaInvestimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "valor")
    private double valor;
    @Column(name = "tipo")
    private String tipo;
    @Transient
    private String descTipo;
    @JoinColumn(name = "patrimonioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patrimonio patrimonioid;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

    public DespesaInvestimento() {
    }

    public DespesaInvestimento(Integer id) {
        this.id = id;
    }

    public DespesaInvestimento(Integer id, Date data, String observacao, double valor, String tipo) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
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
        if (!(object instanceof DespesaInvestimento)) {
            return false;
        }
        DespesaInvestimento other = (DespesaInvestimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.DespesaInvestimento[ id=" + id + " ]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Patrimonio getPatrimonioid() {
        return patrimonioid;
    }

    public void setPatrimonioid(Patrimonio patrimonioid) {
        this.patrimonioid = patrimonioid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescTipo() {
        if (this.tipo.equals("I")) {
            return "Investimento";
        } else if (this.tipo.equals("D")) {
            return "Despesa";
        } else {
            return "";
        }
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }
    
}