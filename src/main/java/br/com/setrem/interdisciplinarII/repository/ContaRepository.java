package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}