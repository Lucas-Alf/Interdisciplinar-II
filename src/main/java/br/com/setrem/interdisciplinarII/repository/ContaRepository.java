package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.setrem.interdisciplinarII.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query(value="SELECT * FROM conta c WHERE c.descricao LIKE %?1% AND c.cliforid = ?2", nativeQuery = true)
    public List<Conta> pesquisar(String nome, String id);

    @Query(value="SELECT * FROM conta c WHERE c.sintetica = true AND c.cliforid = ?1", nativeQuery = true)
    public List<Conta> pesquisarSint(String id);

    @Query(value="SELECT * FROM conta c WHERE c.cliforid = ?1", nativeQuery = true)
    public List<Conta> findByCliForid(String id);
}