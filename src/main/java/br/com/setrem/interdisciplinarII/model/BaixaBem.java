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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lukin
 */


@Entity(name = "baixabem")
public class BaixaBem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "motivobaixaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotivoBaixa motivobaixaid;
    @JoinColumn(name = "patrimonioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patrimonio patrimonioid;

    public BaixaBem() {
    }

    public BaixaBem(Integer id) {
        this.id = id;
    }

    public BaixaBem(Integer id, Date data, BigDecimal valor, String observacao) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public MotivoBaixa getMotivobaixaid() {
        return motivobaixaid;
    }

    public void setMotivobaixaid(MotivoBaixa motivobaixaid) {
        this.motivobaixaid = motivobaixaid;
    }

    public Patrimonio getPatrimonioid() {
        return patrimonioid;
    }

    public void setPatrimonioid(Patrimonio patrimonioid) {
        this.patrimonioid = patrimonioid;
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
        if (!(object instanceof BaixaBem)) {
            return false;
        }
        BaixaBem other = (BaixaBem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Baixabem[ id=" + id + " ]";
    }
    
}
