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

@Entity(name = "produto")

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
   // @NotNull
   //@Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
   // @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    //@NotNull
    @Column(name = "quantidademinima")
    private BigDecimal quantidademinima;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtoid")
    private Collection<Patrimonio> patrimonioCollection;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor cliforid;
    @JoinColumn(name = "grupoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupo grupoid;
    @JoinColumn(name = "marcaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Marca marcaid;
    @JoinColumn(name = "unidademedidaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UnidadeMedida unidademedidaid;
    //@JoinColumn(name = "precoid", referencedColumnName = "id")
    //@ManyToOne(optional = false)
    //private Preco precoid;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String nome, String descricao, BigDecimal quantidademinima) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidademinima = quantidademinima;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQuantidademinima() {
        return quantidademinima;
    }

    public void setQuantidademinima(BigDecimal quantidademinima) {
        this.quantidademinima = quantidademinima;
    }

    @XmlTransient
    public Collection<Patrimonio> getPatrimonioCollection() {
        return patrimonioCollection;
    }

    public void setPatrimonioCollection(Collection<Patrimonio> patrimonioCollection) {
        this.patrimonioCollection = patrimonioCollection;
    }


    public Grupo getGrupoid() {
        return grupoid;
    }

    public void setGrupoid(Grupo grupoid) {
        this.grupoid = grupoid;
    }

    public Marca getMarcaid() {
        return marcaid;
    }

    public void setMarcaid(Marca marcaid) {
        this.marcaid = marcaid;
    }

    public UnidadeMedida getUnidademedidaid() {
        return unidademedidaid;
    }

    public void setUnidademedidaid(UnidadeMedida unidademedidaid) {
        this.unidademedidaid = unidademedidaid;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.Produto[ id=" + id + " ]";
    }

    public CliFor getCliforid() {
        return cliforid;
    }

    public void setCliforid(CliFor cliforid) {
        this.cliforid = cliforid;
    }

}