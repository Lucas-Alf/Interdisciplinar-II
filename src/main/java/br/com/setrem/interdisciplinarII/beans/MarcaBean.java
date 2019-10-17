
package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.Marca;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.MarcaRepository;
import javax.inject.Named;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.web.context.request.RequestContextListener;



@Named(value = "marcaBean")
@SessionScoped
public class MarcaBean implements Serializable {

    @Autowired
    private MarcaRepository marcaRepository;
    private Marca marca =  new Marca();

    private int id;
    private String nome;
    private List<Marca> marcas;

    public MarcaBean() {

    }

    public void Salvar(String nome) {
        if (nome == "") {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            Marca est = new Marca();
            est.setNome(nome);
            marcaRepository.save(est);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
        }
    }

    public List<Marca> ListarTabela() {
        return marcaRepository.findAll();
    }

    public void AtualizarTabela() {
        this.marcas = marcaRepository.findAll();
    }

    public void Pesquisar(String nome) {
        this.marcas = marcaRepository.pesquisar(nome);
    }

    public void Deletar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            marcaRepository.deleteById(id);
            this.AtualizarTabela();
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            marca = marcaRepository.getOne(id);
            int codigo = marca.getId();
            String nome = marca.getNome();
            marca.setId(codigo);
            marca.setNome(nome);
            PrimeFaces.current().executeScript("$('#AlterarMarca').modal('show');");
        }
    }

    public void Alterar() {
        marcaRepository.save(marca);
        this.AtualizarTabela(); 
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");    
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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

    public List<Marca> getMarcas() {
        if (this.marcas == null) {
            this.marcas = marcaRepository.findAll();
        }
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

}

