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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * 
 */
@Entity(name = "conta")

public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    // @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @NotNull
    @Column(name = "sintetica")
    private boolean sintetica;

    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor cliforid;

    @JoinColumn(name = "contapai", referencedColumnName = "id")
    @ManyToOne()
    private Conta contapai;

    @JoinColumn(name = "ordem")
    @Basic(optional = false)
    private int ordem;

 // TABELA NA GRID SUCESSO
    private String hierarquia;

    // CAMPO NIVEL SINTETICO

    // FAZER TRANSACAO, METODO VAI RECEBER UMA LISTA DE LANCAMENTOS CONTABEIS
    public Conta() {
    }

    public Conta(Integer id) {
        this.id = id;
    }

    public Conta(Integer id, String descricao, boolean sintetica, Conta contapai) {
        this.id = id;
        this.descricao = descricao;
        this.sintetica = sintetica;
        this.contapai = contapai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSintetica() {
        return sintetica;
    }

    public void setSintetica(boolean sintetica) {
        this.sintetica = sintetica;
    }

    public Conta getContapai() {
        return contapai;
    }

    public void setContapai(Conta contapai) {
        this.contapai = contapai;
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
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public CliFor getCliforid() {
        return cliforid;
    }

    public void setCliforid(CliFor cliforid) {
        this.cliforid = cliforid;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

}
