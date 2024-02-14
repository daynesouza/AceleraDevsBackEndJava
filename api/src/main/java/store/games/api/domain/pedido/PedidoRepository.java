package store.games.api.domain.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.games.api.domain.client.Client;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClient(Client client);

    @Query("SELECT p FROM Pedido p JOIN FETCH p.itens WHERE dataCompra BETWEEN :dataInicialSQL and :dataFinalSQL")
    List<DadosPedidoBanco> buscarPedidosInicioEFimMes(Date dataInicialSQL, Date dataFinalSQL);

    //List<DadosPedidoBanco> findByDataCompraBetween(Date dataInicialSQL, Date dataFinalSQL);

    //@Query("select p from Pedido p where p.client = :pClient")
    //Pedido listarTodosPedidosCliente(Client pClient);
    //List<Pedido> listarTodosPedidosCliente(Long teste);
}
