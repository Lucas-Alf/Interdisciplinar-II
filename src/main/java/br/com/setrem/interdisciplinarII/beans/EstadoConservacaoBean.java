package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.EstadoConservacaoRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "estadoConservacaoBean")
@SessionScoped
public class EstadoConservacaoBean implements Serializable {

    @Autowired
    private EstadoConservacaoRepository estadoConservacaoRepository;

    public EstadoConservacaoBean() {
    }

    public void SalvarEstado(String descricao) {
        EstadoConservacao est = new EstadoConservacao();
        est.setDescricao(descricao);
        estadoConservacaoRepository.save(est);
    }

}
