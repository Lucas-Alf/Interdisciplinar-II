package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CentroCusto;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.repository.CentroCustoRepository;

@Named(value = "centroCustoBean")
@SessionScoped
public class CentroCustoBean implements Serializable {

    @Autowired
    private CentroCustoRepository centroCustoRepository;
    private CentroCusto centroCusto = new CentroCusto();

    private int id;
    private String nome;
    private CliFor CliForid;
    private List<CentroCusto> centrosCustos;

    public CentroCustoBean() {
    }

    public void Insert() {
        if (this.centroCusto.getNome().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao", fm);
        } /*else if (this.centroCusto.getNome().length() > 80) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!",
                    "Centro de custo nao pode conter mais que 80 caracteres.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }*/ else {
            CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
            this.centroCusto.setCliforid(empresa);
            centroCustoRepository.save(this.centroCusto);
            this.AtualizarTable();   
            
            FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
            PrimeFaces.current().executeScript("$('#CadastrarCentroCusto').modal('hide');");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        }
    }

    public List<CentroCusto> ListarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        return centroCustoRepository.findByCliForid(empresa.getId());
    }

    public void AtualizarTable() {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
        this.centrosCustos = centroCustoRepository.findByCliForid(empresa.getId());
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            try{
                centroCustoRepository.deleteById(id);
                this.AtualizarTable();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            } catch(Exception ex){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Registro é utilizado por Lançamento(s) Contábil(eis) ou Patrimônio(s).");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("validacao2", fm);
            }
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
                    "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("validacao2", fm);
        } else {
            centroCusto = centroCustoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarCentroCusto').modal('show');");
        }
    }

    public void Pesquisar(String nome) {
        CliFor empresa = (CliFor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("empresa");
         this.centrosCustos = centroCustoRepository.pesquisar(nome, empresa.getId());
    }

    public void AbrirModal() {
        this.centroCusto = new CentroCusto();
        PrimeFaces.current().executeScript("$('#CadastrarCentroCusto').modal('show');");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CentroCusto> getCentrosCustos() {
        if (this.centrosCustos == null) {
            this.centrosCustos = centroCustoRepository.findAll();
        }
        return centrosCustos;
    }

    public void setCentrosCustos(List<CentroCusto> centrosCustos) {
        this.centrosCustos = centrosCustos;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }

}
