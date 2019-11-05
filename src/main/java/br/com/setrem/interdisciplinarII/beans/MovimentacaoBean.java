package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.Movimentacao;
import br.com.setrem.interdisciplinarII.repository.MovimentacaoRepository;

@Named(value = "movimentacaoBean")
@SessionScoped
public class MovimentacaoBean implements Serializable {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    private Movimentacao movimentacao = new Movimentacao();

    private int id;
    private List<Movimentacao> movimentacoes;

    public MovimentacaoBean() {

    }

}