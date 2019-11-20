

package br.com.setrem.interdisciplinarII.model;

@Entity(name = contapagar)
public class ContaPagar implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
   //@Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "marcaid")
    //private Collection<Produto> produtoCollection;

public ContaPagar {
}
public ContaPagar (Integer id){
    this.id = id;
}
public ContaPagar(Integer id, String descricao, String NumDocumento, String SerieDocumento, date DataDocumento, 
date DataVencimento, date DataPagamento, double Valor, double Saldo) {
        this.id = id;
        this.descricao = descricao;
        this.numdocumento = numdocumento;
        this.seriedocumento = seriedocumento;
        this.datadocumento = datadocumento;
        this.datavencimento = datavencimento;
        this.datapagamento = datapagamento;
        this.valor = valor;
        this.saldo = saldo;
    }

}
