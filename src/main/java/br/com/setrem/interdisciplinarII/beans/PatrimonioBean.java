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
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.patrimonios = patrimonioRepository.listaPatrimonio(empresa.getId());
    }

    public void BaixarBem(int id) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        patrimonioRepository.BaixarBem(id, empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.patrimonios = patrimonioRepository.pesquisar(descricao, empresa.getId());
    }

    public void ListaPatrimonio() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.patrimonios = patrimonioRepository.listaPatrimonio(empresa.getId());
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
        if (this.patrimonio.getDataaquisicao() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Data de Aquisição.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if (this.patrimonio.getProdutoid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Patrimônio.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if ((int)(this.patrimonio.getValor()) == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if (this.patrimonio.getFornecedorid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Fornecedor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if (this.patrimonio.getGrupoBemid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Grupo do Bem.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if (this.patrimonio.getEstadoConservacaoid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Estado de Conservação.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else if (this.patrimonio.getCentroCustoid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Centro de Custo.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            patrimonio.setCliForid(empresa);
            patrimonioRepository.save(this.patrimonio);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            this.LancarDepreciacao();
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            patrimonioRepository.deleteById(id);
            this.AtualizarTabela();

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro deletado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            this.patrimonio = patrimonioRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarPatrimonio').modal('show');");
        }
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
        this.patrimonio = patrimonioRepository.getOne(id);
        this.patrimonio.setBaixado(1);
        patrimonioRepository.save(patrimonio);
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