package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Depreciacao;

@Repository
public interface DepreciacaoRepository extends JpaRepository<Depreciacao, Integer> {

    @Query(value = "select * from depreciacao order by id asc", nativeQuery = true)
    public List<Depreciacao> AtualizarTabela();

    @Query(value = "select * from depreciacao where patrimonioid = ?1 order by id asc", nativeQuery = true)
    public List<Depreciacao> Pesquisar(int id);

    @Query(value = "select count(*) from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public int VerificaDepreciacao(int id);

    @Query(value = "select * from depreciacao where depreciacao = 0 and mes between ?1 and ?2 and ano between ?3 and ?4", nativeQuery = true)
    public List<Depreciacao> ListarFaltaDepreciar(int mesI, int mesF, int anoI, int anoF);

    @Query(value = "update depreciacao set depreciacao = 1, datadepreciacao = current_date where id = ?1", nativeQuery = true)
    public int AlterarDepreciar(int idDepreciacao);

    @Query(value = "delete from depreciacao where patrimonioid = ?1 and depreciacao = 0", nativeQuery = true)
    public Void ExcluirDepreciacao(int patrimonioId);

    @Query(value = "select * from depreciacao where patrimonioid = ?1 and depreciacao = 1", nativeQuery = true)
    public int CountDepreciados(int patrimonioId);

    @Query(value = "select * from depreciacao where patrimonioid = ?1 order by mes desc, ano desc limit 1", nativeQuery = true)
    public List<Depreciacao> ListaUltimoDepreciado(int patrimonioId);

}