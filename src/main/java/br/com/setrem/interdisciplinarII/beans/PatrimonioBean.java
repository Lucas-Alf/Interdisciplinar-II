package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.InterdisciplinarIIApplication;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.UsuarioRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaQuery;
import com.google.gson.Gson;
import javax.annotation.ManagedBean;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "patrimonioBean")
@SessionScoped
public class PatrimonioBean implements Serializable {

}