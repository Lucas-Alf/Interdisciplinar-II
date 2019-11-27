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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "recebimento")

public class Recebimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "valorrecebido")
    private  double valorrecebido;
    @Column(name = "datarecebimento")
    @Temporal(TemporalType.DATE)
    private Date datarecebimento;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "contareceberid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContaReceber ContaReceberid;
    @JoinColumn(name = "especieid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Especie Especieid;
    @JoinColumn(name = "contacorrenteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContaCorrente ContaCorrenteid;
    @JoinColumn(name = "cliforid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CliFor CliForid;

public Recebimento() {
    }

    public Recebimento(Integer id) {
        this.id = id;
    }

    public Recebimento(Integer id, double valorrecebido, Date datarecebimento, String descricao) {
        this.id = id;
        this.valorrecebido = valorrecebido;
        this.datarecebimento = datarecebimento;
        this.descricao = descricao;
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValorrecebido() {
        return valorrecebido;
    }

    public void setValorrecebido(double valorrecebido) {
        this.valorrecebido = valorrecebido;
    }

    public Date getDatarecebimento() {
        return datarecebimento;
    }

    public void setDatarecebimento(Date datarecebimento) {
        this.datarecebimento = datarecebimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContaReceber getContaReceberid() {
        return ContaReceberid;
    }

    public void setContaReceberid(ContaReceber ContaReceberid) {
        this.ContaReceberid = ContaReceberid;
    }

    public Especie getEspecieid() {
        return Especieid;
    }

    public void setEspecieid(Especie Especieid) {
        this.Especieid = Especieid;
    }

    public ContaCorrente getContaCorrenteid() {
        return ContaCorrenteid;
    }

    public void setContaCorrenteid(ContaCorrente ContaCorrenteid) {
        this.ContaCorrenteid = ContaCorrenteid;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor CliForid) {
        this.CliForid = CliForid;
    }
}