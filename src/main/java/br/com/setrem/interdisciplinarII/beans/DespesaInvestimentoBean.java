package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Depreciacao;
import br.com.setrem.interdisciplinarII.model.DespesaInvestimento;
import br.com.setrem.interdisciplinarII.repository.DepreciacaoRepository;
import br.com.setrem.interdisciplinarII.repository.DespesaInvestimentoRepository;

@Named(value = "despesaInvestimentoBean")
@SessionScoped
public class DespesaInvestimentoBean implements Serializable {

    @Autowired
    private DespesaInvestimentoRepository despesaInvestimentoRepository;
    private DespesaInvestimento despesaInvestimento = new DespesaInvestimento();

    @Autowired
    private DepreciacaoRepository depreciacaoRepository;
    private Depreciacao depreciacao = new Depreciacao();

    private List<DespesaInvestimento> despesaInvestimentos;
    private List<Depreciacao> depreciacoes;

    public DespesaInvestimentoBean() {

    }

    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.despesaInvestimentos = despesaInvestimentoRepository.AtualizarTabela(empresa.getId());
    }

    public void AbrirModal() {
        this.despesaInvestimento = new DespesaInvestimento();
        PrimeFaces.current().executeScript("$('#CadastrarDespesaInvestimento').modal('show');");
    }

    public void Salvar() {
        if (this.despesaInvestimento.getTipo() == "") {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Tipo do Lançamento.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.despesaInvestimento.getData() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Data.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.despesaInvestimento.getPatrimonioid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Patrimônio.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if ((int)(this.despesaInvestimento.getValor()) == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Valor.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            despesaInvestimento.setCliForid(empresa);
            despesaInvestimentoRepository.save(this.despesaInvestimento);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarDespesaInvestimento').modal('hide');");

            if (despesaInvestimento.getTipo().equals("I")) {
                if (despesaInvestimento.getPatrimonioid().isDepreciavel() == true) {
                    int patrimonioId = despesaInvestimento.getPatrimonioid().getId();                  
                    
                    this.depreciacoes = depreciacaoRepository.ListaDepreciacao(patrimonioId);

                    this.depreciacao = depreciacoes.get(0);
                    double valorAtualizado = depreciacao.getValoratualizado();
                    double valorReavaliado = valorAtualizado + despesaInvestimento.getValor();

                    double valorMensal = valorReavaliado * (depreciacao.getTaxadepreciacaomensal() / 100);
                    double valorAnual = valorMensal * 12;
                    double valorAtualizadoCerto;
                    
                    for (int i = 0; i < this.depreciacoes.size(); i++) {
                        this.depreciacao = depreciacoes.get(i);
                        
                        valorAtualizadoCerto = valorReavaliado - (valorMensal * (i + 1));

                        if (valorAtualizadoCerto < 0) {
                            break;
                        } else {
                            depreciacao.setValordepreciado(valorReavaliado - valorAtualizadoCerto);
                            depreciacao.setValorreavaliado(valorReavaliado);
                            depreciacao.setValoratualizado(valorAtualizadoCerto);
                            depreciacao.setDepreciacao(0);
                            depreciacao.setValoranual(valorAnual);
                            depreciacao.setValormes(valorMensal);
                            depreciacaoRepository.save(depreciacao);
                        }
                    }
                }
            }
    
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public void Deletar(int id) {
        try {
            if (id == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            } else {
                despesaInvestimentoRepository.deleteById(id);
                this.AtualizarTabela();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!","Não é possível excluir, pois possui relação com outro registro.");
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
            despesaInvestimento = despesaInvestimentoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarDespesaInvestimento').modal('show');");
        }
    }

    public DespesaInvestimentoRepository getDespesaInvestimentoRepository() {
        return despesaInvestimentoRepository;
    }

    public void setDespesaInvestimentoRepository(DespesaInvestimentoRepository despesaInvestimentoRepository) {
        this.despesaInvestimentoRepository = despesaInvestimentoRepository;
    }

    public DespesaInvestimento getDespesaInvestimento() {
        return despesaInvestimento;
    }

    public void setDespesaInvestimento(DespesaInvestimento despesaInvestimento) {
        this.despesaInvestimento = despesaInvestimento;
    }

    public List<DespesaInvestimento> getDespesaInvestimentos() {
        return despesaInvestimentos;
    }

    public void setDespesaInvestimentos(List<DespesaInvestimento> despesaInvestimentos) {
        this.despesaInvestimentos = despesaInvestimentos;
    }

    public DepreciacaoRepository getDepreciacaoRepository() {
        return depreciacaoRepository;
    }

    public void setDepreciacaoRepository(DepreciacaoRepository depreciacaoRepository) {
        this.depreciacaoRepository = depreciacaoRepository;
    }

    public List<Depreciacao> getDepreciacoes() {
        return depreciacoes;
    }

    public void setDepreciacoes(List<Depreciacao> depreciacoes) {
        this.depreciacoes = depreciacoes;
    }

    public Depreciacao getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(Depreciacao depreciacao) {
        this.depreciacao = depreciacao;
    }

}