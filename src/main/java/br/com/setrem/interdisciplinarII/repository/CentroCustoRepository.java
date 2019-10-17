package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.CentroCusto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {

    @Query("SELECT c FROM centrocusto c WHERE c.nome LIKE %?1%")
    public List<CentroCusto> pesquisar(String nome);
}