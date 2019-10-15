package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.CliFor;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.CliForRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "cliforBean")
@SessionScoped
public class CliForBean implements Serializable {

    @Autowired
    private CliForRepository cliforRepository;

    public CliForBean() {
    }

    public void Insert(String cnpj, String cpf, Character tipopessoa, String nome, String nomefantasia,
                        String email, String telefone, String celular, String tipocliente) {
        CliFor clifor = new CliFor();
        clifor.setCnpj(cnpj);
        clifor.setCpf(cpf);
        clifor.setTipopessoa(tipopessoa);
        clifor.setNome(nome);
        clifor.setNomefantasia(nomefantasia);
        clifor.setEmail(email);
        clifor.setTelefone(telefone);
        clifor.setCelular(celular);
        clifor.setTipocliente(tipocliente);

        cliforRepository.save(clifor);
    }

    public List<CliFor> AtualizarTable() {
        return cliforRepository.findAll();
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione um registro para excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);

        } else {
            cliforRepository.deleteById(id);
        }

    }

}
