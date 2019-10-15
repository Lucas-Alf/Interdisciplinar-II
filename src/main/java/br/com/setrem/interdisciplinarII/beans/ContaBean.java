package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.SessionFactory;
import br.com.setrem.interdisciplinarII.model.Conta;
import br.com.setrem.interdisciplinarII.model.Usuario;
import br.com.setrem.interdisciplinarII.repository.ContaRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Named(value = "contaBean")
@SessionScoped
public class ContaBean implements Serializable {

    @Autowired
    private ContaRepository contaRepository;

    public ContaBean() {
    }

    public void Insert(String descricao, BigDecimal valor/*, Conta contaPai */) {
        Conta conta = new Conta();
        conta.setDescricao(descricao);
        conta.setValor(valor);
        //conta.setContaPai(contaPai);
        //conta.setCliForid();
        contaRepository.save(conta);
    }

    public List<Conta> Listar() {
        return contaRepository.findAll();
    }

    public void Remove(int id) {
        if (id == 0) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Selecione um registro para excluir.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);

        } else {
            contaRepository .deleteById(id);
        }

    }

}
