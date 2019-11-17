package br.com.setrem.interdisciplinarII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Auditoria;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {

}