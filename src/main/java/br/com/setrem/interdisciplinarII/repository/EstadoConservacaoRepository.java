package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EstadoConservacaoRepository extends JpaRepository<EstadoConservacao, Integer> {

    @Query(value = "select * from estadoconservacao where descricao like %?1% and cliforid = ?2", nativeQuery = true)
    public List<EstadoConservacao> Pesquisar(String descricao, String empresa);

    @Query(value = "select * from estadoconservacao where cliforid = ?1", nativeQuery = true)
    public List<EstadoConservacao> AtualizarTabela(String empresa);

}