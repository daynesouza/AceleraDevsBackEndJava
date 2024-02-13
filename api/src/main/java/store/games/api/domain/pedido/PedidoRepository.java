package store.games.api.domain.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.games.api.domain.client.Client;
import store.games.api.domain.pedido.Pedido;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClient(Client client);

    //@Query("select p from Pedido p where p.client = :pClient")
    //Pedido listarTodosPedidosCliente(Client pClient);
    //List<Pedido> listarTodosPedidosCliente(Long teste);
}
