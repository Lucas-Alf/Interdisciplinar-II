package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.BaixaBem;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Historico;
import br.com.setrem.interdisciplinarII.model.LancamentoContabil;
import br.com.setrem.interdisciplinarII.model.Patrimonio;
import br.com.setrem.interdisciplinarII.repository.BaixaBemRepository;
import br.com.setrem.interdisciplinarII.repository.HistoricoRepository;
import br.com.setrem.interdisciplinarII.repository.LancamentoContabilRepository;
import br.com.setrem.interdisciplinarII.repository.PatrimonioRepository;
import br.com.setrem.interdisciplinarII.repository.CentroCustoRepository;
import br.com.setrem.interdisciplinarII.repository.ContaRepository;

@Named(value = "baixaBemBean")
@SessionScoped
public class BaixaBemBean implements Serializable {

    @Autowired
    private CentroCustoRepository centroCustoRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BaixaBemRepository baixaBemRepository;
    private BaixaBem baixaBem = new BaixaBem();
    private Patrimonio patrimonio = new Patrimonio();
    @Autowired
    private PatrimonioRepository patrimonioRepository;

    private List<BaixaBem> baixaBens;

    public BaixaBemBean() {

    }

    public void AtualizarTabela() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.baixaBens = baixaBemRepository.AtualizarTabela(empresa.getId());
    }

    public void Pesquisar(String descricao) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.baixaBens = baixaBemRepository.Pesquisar(descricao, empresa.getId());
    }

    public void AbrirModal() {
        this.baixaBem = new BaixaBem();
        PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
    }

    public void Salvar() {
        if (this.baixaBem.getData() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe a Data.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.baixaBem.getPatrimonioid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Patrimônio.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if (this.baixaBem.getMotivobaixaid() == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Motivo de Baixa.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else if ((int) (this.baixaBem.getValor()) == 0
                && this.baixaBem.getMotivobaixaid().getDescricao().equals("Venda")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe o Valor da Venda.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .get("empresa");
            this.patrimonio = patrimonioRepository.getOne(this.baixaBem.getPatrimonioid().getId());
            this.patrimonio.setBaixado(1);
            patrimonioRepository.save(patrimonio);
            this.baixaBem.setCliForid(empresa);
            baixaBemRepository.save(this.baixaBem);
            this.AtualizarTabela();

            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Patrimônio Baixado.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);

            Historico his = historicoRepository.trazHistorico("Baixa Patrimônio");
            his.getHistorico().replace("{CODIGO}", baixaBem.getId().toString());
            his.getHistorico().replace("{MODULO}", "PATRIMONIO");

            List<LancamentoContabil> listaLan = new ArrayList<>();

            LancamentoContabil lanC = new LancamentoContabil();
            lanC.setCliforid(empresa);
            lanC.setDatahora(new Date());
            lanC.setHistorico(his.getHistorico());
            lanC.setTipo("C");
            lanC.setCentrocustoid(centroCustoRepository.trazCentroCusto(empresa.getId(), "Almoxarifado"));
            lanC.setIdconta(contaRepository.trazConta(empresa.getId(), "OUTROS IMOBILIZADOS"));
            lanC.setValor(this.baixaBem.getValor());

            LancamentoContabil lanD = new LancamentoContabil();
            lanD.setCliforid(empresa);
            lanD.setDatahora(new Date());
            lanD.setHistorico(his.getHistorico());
            lanD.setTipo("D");
            lanD.setCentrocustoid(centroCustoRepository.trazCentroCusto(empresa.getId(), "Almoxarifado"));
            lanD.setIdconta(contaRepository.trazConta(empresa.getId(), "DESPESA OU CUSTO COM BAIXA DE AI"));
            lanD.setValor(this.baixaBem.getValor());

            listaLan.add(lanC);
            listaLan.add(lanD);

            LancamentoContabilBean bean = new LancamentoContabilBean();

            bean.LancamentoContabil(listaLan);
        }
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            baixaBemRepository.deleteById(id);
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
            baixaBem = baixaBemRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarBaixaBem').modal('show');");
        }
    }

    public void Alterar() {
        baixaBemRepository.save(baixaBem);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public BaixaBemRepository getBaixaBemRepository() {
        return baixaBemRepository;
    }

    public void setBaixaBemRepository(BaixaBemRepository baixaBemRepository) {
        this.baixaBemRepository = baixaBemRepository;
    }

    public BaixaBem getBaixaBem() {
        return baixaBem;
    }

    public void setBaixaBem(BaixaBem baixaBem) {
        this.baixaBem = baixaBem;
    }

    public List<BaixaBem> getBaixaBens() {
        return baixaBens;
    }

    public void setBaixaBens(List<BaixaBem> baixaBens) {
        this.baixaBens = baixaBens;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public PatrimonioRepository getPatrimonioRepository() {
        return patrimonioRepository;
    }

    public void setPatrimonioRepository(PatrimonioRepository patrimonioRepository) {
        this.patrimonioRepository = patrimonioRepository;
    }

}