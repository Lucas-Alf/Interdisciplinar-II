

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
    @Column(name = "Descrição")
    private String descricao;
    @Column(name = "Número do documento")
    private Number numdocumento;
    @Column(name = "Série do Documento")
    private Number seriedocumento;
    @Column(name = "Data do Documento")
    private Date datadocumento;
    @Column(name = "Data de Vencimento")
    private Date datavencimento;
    @Column(name = "Data de Pagamento")
    private Date datapagamento;
    @Column(name = "Valor")
    private Double valor;
    @Column(name = "Saldo")
    private Double saldo;
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
