package br.com.setrem.interdisciplinarII.repository;

import br.com.setrem.interdisciplinarII.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
