package store.games.api.domain.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<DadosListagemEstoque> findByDataBetween(Date dataInicialSQL, Date dataFinalSQL);

    @Query("SELECT e FROM Estoque e WHERE e.data BETWEEN :dataInicialSQL and :dataFinalSQL")
    List<DadosListagemEstoque> buscarEstoqueInicioEFimMes(Date dataInicialSQL, Date dataFinalSQL);
}
