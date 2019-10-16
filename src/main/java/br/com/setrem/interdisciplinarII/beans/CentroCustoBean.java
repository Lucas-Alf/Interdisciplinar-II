package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.CentroCusto;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.CentroCustoRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "centroCustoBean")
@SessionScoped
public class CentroCustoBean implements Serializable {

    @Autowired
    private CentroCustoRepository centroCustoRepository;
    private CentroCusto centroCusto =  new CentroCusto();

    private int id;
    private String nome;
    private CliFor CliForid;
    private List<CentroCusto> centrosCustos;

    public CentroCustoBean() {
    }

    public void Insert(String nome/*, CliFor CliForid*/) {
        CentroCusto centCust = new CentroCusto();
        centCust.setNome(nome);
        //centCust.setCliForid(CliForid);
        PrimeFaces.current().executeScript("$('#CadastrarCentroCusto').modal('hide');");
        centroCustoRepository.save(centCust);
        this.AtualizarTable();
    }

    public List<CentroCusto> ListarTable() {
        return centroCustoRepository.findAll();
    }

    public void AtualizarTable() {
        this.centrosCustos = centroCustoRepository.findAll();
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            centroCustoRepository.deleteById(id);
            this.AtualizarTable();
        }

    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            centroCusto = centroCustoRepository.getOne(id);
            int codigo = centroCusto.getId();
            String nome = centroCusto.getNome();
            centroCusto.setId(codigo);
            centroCusto.setNome(nome);
            PrimeFaces.current().executeScript("$('#AlterarCentroCusto').modal('hide');");
        }
    }

    public void Alterar() {
        centroCustoRepository.save(centroCusto);
        this.AtualizarTable(); 
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

    public CliFor getCliForid() {
        return CliForid;
    }

    public void setCliForid(CliFor cliForid) {
        CliForid = cliForid;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    

}
