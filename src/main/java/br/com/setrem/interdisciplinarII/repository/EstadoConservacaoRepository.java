package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.EstadoConservacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EstadoConservacaoRepository extends JpaRepository<EstadoConservacao, Integer> {

}