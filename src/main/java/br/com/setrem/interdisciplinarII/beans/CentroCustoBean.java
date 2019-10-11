package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.CentroCusto;
import br.com.setrem.interdisciplinarII.repository.CentroCustoRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "centroCustoBean")
@SessionScoped
public class CentroCustoBean implements Serializable {

    @Autowired
    private CentroCustoRepository centroCustoRepository;

    public CentroCustoBean() {
    }

    public void insertAction() {
        centroCustoRepository.save(centroCusto);
        this.centroCusto = new CentroCusto();
        this.centrosCustos = centroCustoRepository.findAll();
    }

    public void atualizarAction() {
        this.centrosCustos = centroCustoRepository.findAll();
    }

    public void selectItem(CentroCusto dpt) {
        this.centroCusto = dpt;
    }

    public void limpaForm() {
        this.centroCusto = new CentroCusto();
    }

    public void removeAction(CentroCusto dpt) {
        centroCustoRepository.delete(dpt);
        this.centrosCustos = centroCustoRepository.findAll();
    }

    private CentroCusto centroCusto = new CentroCusto();
    private List<CentroCusto> centrosCustos = new ArrayList<>();

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public List<CentroCusto> getCentrosCustos() {
        if (this.centrosCustos == null) {
            this.centrosCustos = centroCustoRepository.findAll();
        }
        return centrosCustos;
    }

    public void setCentrosCustos(List<CentroCusto> centrosCustos) {
        this.centrosCustos = centrosCustos;
    }

}
