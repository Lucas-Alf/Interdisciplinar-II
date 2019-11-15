package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.BaixaBem;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Depreciacao;
import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.model.GrupoBem;
import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.model.Produto;
import br.com.setrem.interdisciplinarII.repository.BaixaBemRepository;
import br.com.setrem.interdisciplinarII.repository.DepreciacaoRepository;
import br.com.setrem.interdisciplinarII.repository.PatrimonioRepository;

@Named(value = "patrimonioBean")
@SessionScoped
public class PatrimonioBean implements Serializable {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private DepreciacaoRepository depreciacaoRepository;

    private Depreciacao depreciacao = new Depreciacao();
    private Patrimonio patrimonio = new Patrimonio();
    private Produto produto = new Produto();
    private GrupoBem grupoBem = new GrupoBem();
    private EstadoConservacao estadoConservacao = new EstadoConservacao();

    private List<Patrimonio> patrimonios;

    public PatrimonioBean() {

    }

    public void AtualizarTabela() {
        this.patrimonios = patrimonioRepository.listaPatrimonio();
    }

    public void BaixarBem(int id) {
        patrimonioRepository.BaixarBem(id);
    }

    public void Pesquisar(String descricao) {
        this.patrimonios = patrimonioRepository.pesquisar(descricao);
    }

    public void ListaPatrimonio() {
        this.patrimonios = patrimonioRepository.listaPatrimonio();
    }

    public void AbrirModal() {
        this.patrimonio = new Patrimonio();
        PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
    }

    public void LancarDepreciacao() {
        if (patrimonio.isDepreciavel() == true) {
            if (depreciacaoRepository.VerificaDepreciacao(patrimonio.getId()) == 0) {
                Date data = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(data);
                int mes = cal.get(Calendar.MONTH);
                int ano = cal.get(Calendar.YEAR);
    
                double valor = patrimonio.getValor();
                double depreciacaoTaxa = patrimonio.getGrupoBemid().getTaxadepreciacao();
                double vidaUtil = patrimonio.getGrupoBemid().getVidautil();
                double meses = vidaUtil * 12;
                //double taxaAnual = 100 / vidaUtil;
                //double taxaMensal = 100 / (vidaUtil * 12);
                //double valorMensal = valor / (vidaUtil * 12);
                //double valorAnual = valor / vidaUtil;

                double taxaAnual = depreciacaoTaxa;
                double taxaMensal = depreciacaoTaxa / 12;
                double valorMensal = valor * (taxaMensal / 100);
                double valorAnual = valorMensal * 12;
    
                double valorAtualizado;
    
                for (int i = 1; i <= meses; i++) {
                    if (mes > 12) {
                        mes = 1;
                        ano++;
                    }
                    valorAtualizado = valor - (valorMensal * i);
    
                    if (valorAtualizado < 0) {
                        break;
                    } else {
                        Depreciacao dep = new Depreciacao();
                        dep.setMes(mes);
                        dep.setAno(ano);
                        dep.setValordepreciado(valor - valorAtualizado);
                        dep.setValorreavaliado(0);
                        dep.setVidautil(vidaUtil);
                        dep.setTaxadepreciacaomensal(taxaMensal);
                        dep.setTaxadepreciacaoanual(taxaAnual);
                        dep.setValoratualizado(valorAtualizado);
                        dep.setDepreciacao(0);
                        dep.setValoranual(valorAnual);
                        dep.setValormes(valorMensal);
                        dep.setPatrimonioid(patrimonio);
                        depreciacaoRepository.save(dep);
                        mes++;
                    }
                }
            }
        }
    }

    public void Salvar() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        patrimonio.setCliForid(empresa);
        patrimonioRepository.save(this.patrimonio);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        this.LancarDepreciacao();
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            patrimonioRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro deletado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            this.patrimonio = patrimonioRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        }
    }

    public void Alterar() {
        patrimonioRepository.save(patrimonio);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public void setPatrimonios(List<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
    }

    /*
     * public List<Produto> getProdutos() { return produtos; }
     * 
     * public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
     */

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public GrupoBem getGrupoBem() {
        return grupoBem;
    }

    public void setGrupoBem(GrupoBem grupoBem) {
        this.grupoBem = grupoBem;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public void SalvarBaixa(int id) {
        //patrimonio.setBaixado(1);
        //patrimonioRepository.save(this.patrimonio);
        //patrimonioRepository.BaixarBem(baixaBem.getPatrimonioid());
        //patrimonioRepository.BaixarBem(id);

        //patrimonioRepository.BaixarBem(this.baixaBem.getPatrimonioid().getId());

        //patrimonioBean.BaixarBem(this.baixaBem.getPatrimonioid().getId());


        this.patrimonio = patrimonioRepository.getOne(id);
        this.patrimonio.setBaixado(1);
        patrimonioRepository.save(patrimonio);
        //baixaBemRepository.save(this.baixaBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public Depreciacao getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(Depreciacao depreciacao) {
        this.depreciacao = depreciacao;
    }
    
}