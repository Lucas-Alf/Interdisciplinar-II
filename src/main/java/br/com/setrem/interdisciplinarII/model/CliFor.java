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
@Entity(name = "CliFor")

public class CliFor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cnpj")
    private String cnpj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipopessoa")
    private Character tipopessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nomefantasia")
    private String nomefantasia;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "tipocliente")
    private String tipocliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<CentroCusto> CentroCustoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<Conta> contaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<SolicitacaoRelatorio> SolicitacaoRelatorioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<LancamentoContabil> LancamentoContabilCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorid")
    private Collection<Estoque> estoqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<Estoque> estoqueCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<Relatorio> relatorioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedorid")
    private Collection<Patrimonio> patrimonioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<Patrimonio> patrimonioCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "CliForid")
    private Collection<Produto> produtoCollection;
    @JoinColumn(name = "enderecoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Endereco enderecoid;

    public CliFor() {
    }

    public CliFor(String id) {
        this.id = id;
    }

    public CliFor(String id, String cnpj, String cpf, Character tipopessoa, String nome, String nomefantasia, String email, String telefone, String celular, String tipocliente) {
        this.id = id;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.tipopessoa = tipopessoa;
        this.nome = nome;
        this.nomefantasia = nomefantasia;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.tipocliente = tipocliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Character getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(Character tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    @XmlTransient
    public Collection<CentroCusto> getCentroCustoCollection() {
        return CentroCustoCollection;
    }

    public void setCentroCustoCollection(Collection<CentroCusto> CentroCustoCollection) {
        this.CentroCustoCollection = CentroCustoCollection;
    }

    @XmlTransient
    public Collection<Conta> getContaCollection() {
        return contaCollection;
    }

    public void setContaCollection(Collection<Conta> contaCollection) {
        this.contaCollection = contaCollection;
    }

    @XmlTransient
    public Collection<SolicitacaoRelatorio> getSolicitacaoRelatorioCollection() {
        return SolicitacaoRelatorioCollection;
    }

    public void setSolicitacaoRelatorioCollection(Collection<SolicitacaoRelatorio> SolicitacaoRelatorioCollection) {
        this.SolicitacaoRelatorioCollection = SolicitacaoRelatorioCollection;
    }

    @XmlTransient
    public Collection<LancamentoContabil> getLancamentoContabilCollection() {
        return LancamentoContabilCollection;
    }

    public void setLancamentoContabilCollection(Collection<LancamentoContabil> LancamentoContabilCollection) {
        this.LancamentoContabilCollection = LancamentoContabilCollection;
    }

    @XmlTransient
    public Collection<Estoque> getEstoqueCollection() {
        return estoqueCollection;
    }

    public void setEstoqueCollection(Collection<Estoque> estoqueCollection) {
        this.estoqueCollection = estoqueCollection;
    }

    @XmlTransient
    public Collection<Estoque> getEstoqueCollection1() {
        return estoqueCollection1;
    }

    public void setEstoqueCollection1(Collection<Estoque> estoqueCollection1) {
        this.estoqueCollection1 = estoqueCollection1;
    }

    @XmlTransient
    public Collection<Relatorio> getRelatorioCollection() {
        return relatorioCollection;
    }

    public void setRelatorioCollection(Collection<Relatorio> relatorioCollection) {
        this.relatorioCollection = relatorioCollection;
    }

    @XmlTransient
    public Collection<Patrimonio> getPatrimonioCollection() {
        return patrimonioCollection;
    }

    public void setPatrimonioCollection(Collection<Patrimonio> patrimonioCollection) {
        this.patrimonioCollection = patrimonioCollection;
    }

    @XmlTransient
    public Collection<Patrimonio> getPatrimonioCollection1() {
        return patrimonioCollection1;
    }

    public void setPatrimonioCollection1(Collection<Patrimonio> patrimonioCollection1) {
        this.patrimonioCollection1 = patrimonioCollection1;
    }

    @XmlTransient
    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    public Endereco getEnderecoid() {
        return enderecoid;
    }

    public void setEnderecoid(Endereco enderecoid) {
        this.enderecoid = enderecoid;
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
        if (!(object instanceof CliFor)) {
            return false;
        }
        CliFor other = (CliFor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setrem.interdisciplinarII.model.CliFor[ id=" + id + " ]";
    }
    
}
