package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.CliFor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CliForRepository extends JpaRepository<CliFor, Integer> {

}