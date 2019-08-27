package br.com.setrem.interdisciplinarII.beans;

import br.com.setrem.interdisciplinarII.model.Departamento;
import br.com.setrem.interdisciplinarII.repository.DepartamentoRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Named(value = "departamentoBean")
@SessionScoped
public class DepartamentoBean implements Serializable {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoBean() {
    }

    public void insertAction() {
        departamentoRepository.save(departamento);
        this.departamento = new Departamento();
        this.departamentos = departamentoRepository.findAll();
    }

    public void atualizarAction() {
        this.departamentos = departamentoRepository.findAll();
    }

    public void selectItem(Departamento dpt) {
        this.departamento = dpt;
    }

    public void limpaForm() {
        this.departamento = new Departamento();
    }

    public void removeAction(Departamento dpt) {
        departamentoRepository.delete(dpt);
        this.departamentos = departamentoRepository.findAll();
    }

    private Departamento departamento = new Departamento();
    private List<Departamento> departamentos = new ArrayList<>();

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        if (this.departamentos == null) {
            this.departamentos = departamentoRepository.findAll();
        }
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

}
