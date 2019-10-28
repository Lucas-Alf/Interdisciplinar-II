package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Endereco;
import br.com.setrem.interdisciplinarII.repository.CliForRepository;


@Named(value = "cliforBean")
@SessionScoped
public class CliForBean implements Serializable {

    @Autowired
    private CliForRepository cliforRepository;
    private CliFor cliFor =  new CliFor();

    private String id;
    private String cnpj;
    private String cpf;

    private boolean tipo;
    private Character tipopessoa;

    private String nome;
    private String nomefantasia;
    private String email;
    private String telefone;
    private String celular;
    private String tipocliente;
    private Endereco enderecoid;
    private List<CliFor> clifors;

    public CliForBean() {
    }

    public void Insert() {
        if(!this.cliFor.getNome().isEmpty()){
            if(this.cliFor.getNome().equals("")){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            } else{
                if(tipo){
                    cliFor.setTipopessoa("F".charAt(0));
                } else if (!tipo){
                    cliFor.setTipopessoa("J".charAt(0));
                }
                cliforRepository.save(this.cliFor);
                this.AtualizarTable();
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            }
        } else if (!this.cliFor.getNomefantasia().isEmpty()) {
            if(this.cliFor.getNomefantasia().equals("")){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe um Nome Fantasia!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            } else{
                if(tipo){
                    cliFor.setTipopessoa("F".charAt(0));
                } else if (!tipo){
                    cliFor.setTipopessoa("J".charAt(0));
                }
                cliforRepository.save(this.cliFor);
                this.AtualizarTable();
                PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
            }
        } 
    }

    public List<CliFor> ListarTable() {
        return cliforRepository.findAll();
    }

    public List<CliFor> listaEmpresas() {
        List<CliFor> lista = cliforRepository.ListaEmpresa();
        return lista;
    }

    public List<CliFor> listaFornecedores() {
        List<CliFor> lista = cliforRepository.ListaFornecedor();
        return lista;
    }

    public void AtualizarTable() {
        this.clifors = cliforRepository.findAll();
    }

    public void Remove(String id) {
        if (id.equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            cliforRepository.deleteById(id);
            this.AtualizarTable();
        }

    }

    public void AbreAlterar(String id) {
        if (id.equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            cliFor = cliforRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarCliFor').modal('show');");
        }
    }

    public void Alterar() {
        cliforRepository.save(cliFor);
        this.AtualizarTable(); 
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");
    }

    public void Pesquisar(String nome) {
         this.clifors = cliforRepository.pesquisar(nome);
    }

    public void AbrirModal() {
        this.cliFor = new CliFor();
        PrimeFaces.current().executeScript("$('#CadastrarCliFor').modal('show');");
    }

    public List<CliFor> getClifors() {
        return clifors;
    }

    public void setClifors(List<CliFor> clifors) {
        this.clifors = clifors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Character getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(Character tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Endereco getEnderecoid() {
        return enderecoid;
    }

    public void setEnderecoid(Endereco enderecoid) {
        this.enderecoid = enderecoid;
    }

    public CliFor getCliFor() {
        return cliFor;
    }

    public void setCliFor(CliFor cliFor) {
        this.cliFor = cliFor;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    

}
