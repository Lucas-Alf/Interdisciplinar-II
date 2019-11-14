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

@Entity(name = "depreciacao")
public class Depreciacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "ano")
    private Integer ano;
    @Column(name = "valordepreciado")
    private double valordepreciado;
    @Column(name = "valorreavaliado")
    private double valorreavaliado;
    @Column(name = "Vidautil")
    private double vidautil;
    @Column(name = "taxadepreciacaomensal")
    private double taxadepreciacaomensal;
    @Column(name = "taxadepreciacaoanual")
    private double taxadepreciacaoanual;
    @Column(name = "valoratualizado")
    private double valoratualizado;
    @Column(name = "depreciacao")
    private Integer depreciacao;
    @Transient
    private String descDepreciacao;
    @Column(name = "datadepreciacao")
    @Temporal(TemporalType.DATE)
    private Date datadepreciacao;
    @Column(name = "valoranual")
    private double valoranual;
    @Column(name = "valormes")
    private double valormes;
    @JoinColumn(name = "patrimonioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patrimonio patrimonioid;

    public Depreciacao() {
    }

    public Depreciacao(Integer id) {
        this.id = id;
    }

    public Depreciacao(Integer id, Integer mes, Integer ano, double valordepreciado, double valorreavaliado,
    double vidautil, double taxadepreciacaomensal, double taxadepreciacaoanual, double valoratualizado, Integer depreciacao,
    Date datadepreciacao, double valoranual, double valormes) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.valordepreciado = valordepreciado;
        this.valorreavaliado = valorreavaliado;
        this.vidautil = vidautil;
        this.taxadepreciacaomensal = taxadepreciacaomensal;
        this.taxadepreciacaoanual = taxadepreciacaoanual;
        this.valoratualizado = valoratualizado;
        this.depreciacao = depreciacao;
        this.datadepreciacao = datadepreciacao;
        this.valoranual = valoranual;
        this.valormes = valormes;
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
        if (!(object instanceof Depreciacao)) {
            return false;
        }
        Depreciacao other = (Depreciacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Depreciacao[ id=" + id + " ]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public double getValordepreciado() {
        return valordepreciado;
    }

    public void setValordepreciado(double valordepreciado) {
        this.valordepreciado = valordepreciado;
    }

    public double getValorreavaliado() {
        return valorreavaliado;
    }

    public void setValorreavaliado(double valorreavaliado) {
        this.valorreavaliado = valorreavaliado;
    }

    public double getVidautil() {
        return vidautil;
    }

    public void setVidautil(double vidautil) {
        this.vidautil = vidautil;
    }

    public double getTaxadepreciacaoanual() {
        return taxadepreciacaoanual;
    }

    public void setTaxadepreciacaoanual(double taxadepreciacaoanual) {
        this.taxadepreciacaoanual = taxadepreciacaoanual;
    }

    public double getValoratualizado() {
        return valoratualizado;
    }

    public void setValoratualizado(double valoratualizado) {
        this.valoratualizado = valoratualizado;
    }

    public Integer getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(Integer depreciacao) {
        this.depreciacao = depreciacao;
    }

    public Date getDatadepreciacao() {
        return datadepreciacao;
    }

    public void setDatadepreciacao(Date datadepreciacao) {
        this.datadepreciacao = datadepreciacao;
    }

    public double getValoranual() {
        return valoranual;
    }

    public void setValoranual(double valoranual) {
        this.valoranual = valoranual;
    }

    public double getValormes() {
        return valormes;
    }

    public void setValormes(double valormes) {
        this.valormes = valormes;
    }

    public Patrimonio getPatrimonioid() {
        return patrimonioid;
    }

    public void setPatrimonioid(Patrimonio patrimonioid) {
        this.patrimonioid = patrimonioid;
    }

    public double getTaxadepreciacaomensal() {
        return taxadepreciacaomensal;
    }

    public void setTaxadepreciacaomensal(double taxadepreciacaomensal) {
        this.taxadepreciacaomensal = taxadepreciacaomensal;
    }

    public String getDescDepreciacao() {
        if (this.depreciacao == 0) {
            return "Aberto";
        } else {
            return "Fechado";
        }
    }

    public void setDescDepreciacao(String descDepreciacao) {
        this.descDepreciacao = descDepreciacao;
    }
    
}