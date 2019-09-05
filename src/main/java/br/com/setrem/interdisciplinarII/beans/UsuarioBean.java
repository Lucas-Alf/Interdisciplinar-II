/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioBean() {
    }

    public String Login(String email, String senha) {
        Usuario usuario = usuarioRepository.login(email, senha);
        if (usuario != null) {
            System.out.println("-> Login: " + usuario.getEmail());
            return "/home.xhtml?faces-redirect=true";
        } else {
            System.out.println("-> Usuario não encontrado!");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário não encontrado."));
            return "Usuario não encontrado";
        }
    }

}
// <p:dialog header="Modal Dialog" widgetVar="dlg2" modal="true" height="100">
// <h:outputText value="This is a Modal Dialog." />
// </p:dialog>