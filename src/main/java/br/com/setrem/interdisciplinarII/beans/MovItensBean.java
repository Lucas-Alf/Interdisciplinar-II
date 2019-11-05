package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.MovItens;
import br.com.setrem.interdisciplinarII.repository.MovItensRepository;

@Named(value = "movItenBean")
@SessionScoped
public class MovItensBean implements Serializable {

    @Autowired
    private MovItensRepository movItensRepository;
    private MovItens movItens = new MovItens();

    private int id;
    private List<MovItens> movItenss;

    public MovItensBean() {

    }

}