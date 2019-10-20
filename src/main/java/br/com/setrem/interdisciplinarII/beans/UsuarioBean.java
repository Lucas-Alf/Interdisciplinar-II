/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setrem.interdisciplinarII.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.CliForRepository;
import br.com.setrem.interdisciplinarII.repository.UsuarioRepository;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CliForRepository cliforRepository;

    @Autowired
    private HttpServletRequest request;

    private Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
            .get("usuario");

    private CliFor empresa;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario valor) {
        this.usuario = valor;
    }

    public CliFor getEmpresa() {
        return empresa;
    }

    public void setEmpresa(CliFor valor) {
        this.empresa = valor;
    }

    public UsuarioBean() {
        this.empresa = new CliFor();
    }

    public String Sair() {
        usuario = null;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("usuario")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String VerificaLogin() {
        String urlAtual = request.getRequestURL().toString();
        if (usuario == null) {
            if (urlAtual == null || !urlAtual.contains("index.xhtml")) {
                return "/index.xhtml?faces-redirect=true";
            } else {
                return "";
            }
        } else {
            if (urlAtual.contains("index.xhtml") || urlAtual.endsWith("/")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                        "Autenticado com usuário da sessão. (" + usuario.getEmail() + ")"));
                return "/home.xhtml?faces-redirect=true";
            }
            return "";
        }
    }

    public String Login(String email, String senha) {
        Usuario usuario = usuarioRepository.login(email, senha);
        if (empresa == null || cliforRepository.BuscaPorId(empresa.getId()) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Empresa Inválida."));
            return "Empresa Inválida";
        }
        empresa = cliforRepository.BuscaPorId(empresa.getId());
        if (usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empresa", empresa);
            this.usuario = usuario;
            System.out.println("-> Login: " + usuario.getEmail() + ". Usuário e empresa salvos na sessão!");
            return "/home.xhtml?faces-redirect=true";
        } else {
            System.out.println("-> Usuario não encontrado!");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário não encontrado."));
            return "Usuario não encontrado";
        }
    }
}
