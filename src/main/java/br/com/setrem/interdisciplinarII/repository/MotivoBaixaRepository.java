package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.MotivoBaixa;

@Repository
public interface MotivoBaixaRepository extends JpaRepository<MotivoBaixa, Integer> {

    @Query("SELECT e FROM motivobaixa e WHERE e.descricao LIKE %?1%")
    public List<MotivoBaixa> pesquisar(String descricao);

}
