package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {

    //@Query("SELECT e FROM patrimonio e INNER JOIN e.produtoid t where t.nome LIKE %?1%")
    //public List<Patrimonio> pesquisar(String descricao);
}