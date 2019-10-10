package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;
import br.com.setrem.interdisciplinarII.repository.EstadoConservacaoRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "estadoConservacaoBean")
@SessionScoped
public class EstadoConservacaoBean implements Serializable {

    @Autowired
    private EstadoConservacaoRepository estadoConservacaoRepository;

    public EstadoConservacaoBean() {
    }

    public void insertAction() {
        estadoConservacaoRepository.save(estadoConservacao);
        this.estadoConservacao = new EstadoConservacao();
        this.estadoConservacoes = estadoConservacaoRepository.findAll();
    }

    public void atualizarAction() {
        this.estadoConservacoes = estadoConservacaoRepository.findAll();
    }

    public void selectItem(EstadoConservacao dpt) {
        this.estadoConservacao = dpt;
    }

    public void limpaForm() {
        this.estadoConservacao = new EstadoConservacao();
    }

    public void removeAction(EstadoConservacao dpt) {
        estadoConservacaoRepository.delete(dpt);
        this.estadoConservacoes = estadoConservacaoRepository.findAll();
    }

    private EstadoConservacao estadoConservacao = new EstadoConservacao();
    private List<EstadoConservacao> estadoConservacoes = new ArrayList<>();

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public List<EstadoConservacao> getEstadoConservacoes() {
        if (this.estadoConservacoes == null) {
            this.estadoConservacoes = estadoConservacaoRepository.findAll();
        }
        return estadoConservacoes;
    }

    public void setEstadoConservacoes(List<EstadoConservacao> estadoConservacoes) {
        this.estadoConservacoes = estadoConservacoes;
    }

}
