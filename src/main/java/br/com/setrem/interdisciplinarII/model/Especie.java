package br.com.setrem.interdisciplinarII.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name = "especie")
public class Especie implements Serializable {

private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descricao")
    private String descricao;

public Especie() {
    }

    public Especie(Integer id) {
        this.id = id;
    }

    public Especie(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
