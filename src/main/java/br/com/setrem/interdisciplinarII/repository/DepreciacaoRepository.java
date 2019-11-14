package br.com.setrem.interdisciplinarII.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.setrem.interdisciplinarII.model.Depreciacao;

@Repository
public interface DepreciacaoRepository extends JpaRepository<Depreciacao, Integer> {

    @Query(value = "select * from depreciacao", nativeQuery = true)
    public List<Depreciacao> AtualizarTabela();

    @Query(value = "select * from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public List<Depreciacao> Pesquisar(int id);

    @Query(value = "select count(*) from depreciacao where patrimonioid = ?1", nativeQuery = true)
    public int VerificaDepreciacao(int id);

    @Query(value = "select * from depreciacao where depreciacao = 0 and patrimonioid = ?1 and mes between ?2 and ?3 and ano between ?4 and ?5", nativeQuery = true)
    public List<Depreciacao> ListarFaltaDepreciar(int id, int mesI, int mesF, int anoI, int anoF);

}