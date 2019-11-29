package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.setrem.interdisciplinarII.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    //@Query(value="SELECT * FROM conta c WHERE c.descricao LIKE %?1% AND c.cliforid = ?2 ORDER BY id", nativeQuery = true)
    //public List<Conta> pesquisar(String nome, String id);

    @Query(value="WITH RECURSIVE conts(id, descricao, ordem, name_path) AS ( SELECT id, descricao, ordem, ARRAY[cast(ordem as text)] FROM conta WHERE contapai is null and cliforid = ?1 UNION ALL SELECT p.id, p.descricao, p.ordem, ARRAY_APPEND(t0.name_path, cast(p.ordem as text)) FROM conta p INNER JOIN conts t0 ON t0.id = p.contapai where p.cliforid = ?1) SELECT conts.id, cast(ARRAY_TO_STRING(name_path, '.') as text) hierarquia, conts.descricao,vdd.cliforid, vdd.sintetica, conts.ordem, vdd.contapai FROM conts inner join conta vdd on(conts.id = vdd.id) where sintetica = true order by hierarquia ", nativeQuery = true)
    public List<Conta> pesquisarSint(String id);

    @Query(value="select * from conta where cliforid = ?1 and conta.descricao LIKE %?2% order by hierarquia ", nativeQuery = true)
    public List<Conta> pessquisarGrid(String id, String desc);

    @Query(value="WITH RECURSIVE conts(id, descricao, ordem, name_path) AS ( SELECT id, descricao, ordem, ARRAY[cast(ordem as text)] FROM conta WHERE contapai is null and cliforid = ?1 UNION ALL SELECT p.id, p.descricao, p.ordem, ARRAY_APPEND(t0.name_path, cast(p.ordem as text)) FROM conta p INNER JOIN conts t0 ON t0.id = p.contapai where p.cliforid = ?1) SELECT conts.id, cast(ARRAY_TO_STRING(name_path, '.') as text) hierarquia, conts.descricao,vdd.cliforid, vdd.sintetica, conts.ordem, vdd.contapai FROM conts inner join conta vdd on(conts.id = vdd.id) where sintetica = false order by hierarquia ", nativeQuery = true)
    public List<Conta> pesquisarAnalit(String id);
    
    @Query(value="select * from conta where cliforid = ?1 and descricao = ?2 ", nativeQuery = true)
    public Conta trazConta(String id, String desc);
}