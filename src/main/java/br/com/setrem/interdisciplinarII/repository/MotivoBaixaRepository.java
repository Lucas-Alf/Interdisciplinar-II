package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.MotivoBaixa;

@Repository
public interface MotivoBaixaRepository extends JpaRepository<MotivoBaixa, Integer> {

    @Query(value = "select * from motivobaixa where descricao like %?1% and cliforid = ?2", nativeQuery = true)
    public List<MotivoBaixa> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from motivobaixa where cliforid = ?1", nativeQuery = true)
    public List<MotivoBaixa> AtualizarTabela(String empresa);

}