package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Cidade;
import br.com.setrem.interdisciplinarII.model.Endereco;
import br.com.setrem.interdisciplinarII.repository.EnderecoRepository;

@Named(value = "enderecoBean")
@SessionScoped
public class EnderecoBean implements Serializable {

    @Autowired
    private EnderecoRepository enderecoRepository;
    private Endereco endereco = new Endereco();
    private Cidade cidade = new Cidade();

    private int id;
    private String descricao;
    private List<Endereco> enderecos;

    public EnderecoBean() {

    }

    public void AtualizarTabela() {
        this.enderecos = enderecoRepository.pesquisar("");
    }

    public void Pesquisar(String descricao) {
        this.enderecos = enderecoRepository.pesquisar(descricao);
    }

    public void AbrirModal() {
        this.endereco = new Endereco();
        PrimeFaces.current().executeScript("$('#CadastrarEndereco').modal('show');");
    }

    public void Salvar() {
        if (this.endereco.getRua().equals("")) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Informe uma Descrição!");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            enderecoRepository.save(this.endereco);
            this.AtualizarTabela();
            PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
    }

    public void Deletar(int id) {
        try {
            if (id == 0) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Excluir.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            } else {
                enderecoRepository.deleteById(id);
                this.AtualizarTabela();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Registro deletado.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            }
        } catch (Exception e) {
            if (e.getMessage().contains("could not extract ResultSet")) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atenção!","Não é possível excluir este registro, pois está relacionado!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, fm);
            }
        }
    }

    public void AbreAlterar(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!","Selecione um registro para Alterar.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        } else {
            endereco = enderecoRepository.getOne(id);
            PrimeFaces.current().executeScript("$('#CadastrarEndereco').modal('show');");
        }
    }

    public void Alterar() {
        enderecoRepository.save(endereco);
        this.AtualizarTabela();
        PrimeFaces.current().executeScript("$('.modal-backdrop').hide();");

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro alterado.");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEndereco(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
}