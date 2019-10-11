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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lukin
 */
@Entity(name = "LancamentoContabil")

public class LancamentoContabil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "historico")
    private String historico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lancamentoid")
    private Collection<ContaLancamento> ContaLancamentoCollection;
    @JoinColumn(name = "CentroCustoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CentroCusto CentroCustoid;
    @JoinColumn(name = "CliForid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

    public LancamentoContabil() {
    }

    public LancamentoContabil(Integer id) {
        this.id = id;
    }

    public LancamentoContabil(Integer id, BigDecimal valor, Date data, Date hora, String historico) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.historico = historico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    @XmlTransient
    public Collection<ContaLancamento> getContaLancamentoCollection() {
        return ContaLancamentoCollection;
    }

    public void setContaLancamentoCollection(Collection<ContaLancamento> ContaLancamentoCollection) {
        this.ContaLancamentoCollection = ContaLancamentoCollection;
    }

    public CentroCusto getCentroCustoid() {
        return CentroCustoid;
    }

    public void setCentroCustoid(CentroCusto CentroCustoid) {
        this.CentroCustoid = CentroCustoid;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
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
        if (!(object instanceof LancamentoContabil)) {
            return false;
        }
        LancamentoContabil other = (LancamentoContabil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.LancamentoContabil[ id=" + id + " ]";
    }
    
}
