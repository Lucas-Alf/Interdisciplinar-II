package br.com.setrem.interdisciplinarII.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Depreciacao;

@Repository
public interface DepreciacaoRepository extends JpaRepository<Depreciacao, Integer> {

    @Query(value = "select * from depreciacao where cliforid = ?1 order by id asc", nativeQuery = true)
    public List<Depreciacao> AtualizarTabela(String empresa);

    @Query(value = "select * from depreciacao where patrimonioid = ?1 and cliforid = ?2 order by id asc", nativeQuery = true)
    public List<Depreciacao> Pesquisar(int id, String empresa);

    @Query(value = "select count(*) from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public int VerificaDepreciacao(int id);

    @Query(value = "select * from depreciacao where depreciacao = 0 and cast((ano ||''|| (case when CHARACTER_LENGTH(cast(mes as varchar)) = 1 then cast(0 as varchar) || cast(mes as varchar) else cast(mes as varchar) end) ||'01') as date) between ?1 and ?2", nativeQuery = true)
    public List<Depreciacao> ListarFaltaDepreciar(Date dataI, Date dataF, String empresa);

    @Query(value = "update depreciacao set depreciacao = 1, datadepreciacao = current_date where id = ?1", nativeQuery = true)
    public int AlterarDepreciar(int idDepreciacao);

    @Query(value = "select * from depreciacao where patrimonioid = ?1 and depreciacao = 0 order by id asc", nativeQuery = true)
    public List<Depreciacao> ListaDepreciacao(int patrimonioId);

    @Modifying
    @Transactional
    @Query(value = "delete from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public void DeletarDepreciacao(int patrimonioId);

}