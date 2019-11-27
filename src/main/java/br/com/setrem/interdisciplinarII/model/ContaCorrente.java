package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "contacorrente")

public class ContaCorrente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    private  String descricao;
    @Column(name = "agencia")
    private  double agencia;
    @Column(name = "conta")
    private  String conta;
    @Column(name = "codbanco")
    private  double codbanco;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

public ContaCorrente() {
    }

    public ContaCorrente(Integer id) {
        this.id = id;
    }

    public ContaCorrente(Integer id, String descricao, double agencia, String conta, double codbanco) {
        this.id = id;
        this.descricao = descricao;
        this.agencia = agencia;
        this.conta = conta;
        this.codbanco = codbanco;
        
        
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

    public double getAgencia() {
        return agencia;
    }

    public void setAgencia(double agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(double codbanco) {
        this.codbanco = codbanco;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }
}
