package store.games.api.domain.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.games.api.domain.client.Client;
import store.games.api.domain.itemPedido.ItemPedido;
import store.games.api.domain.product.Product;

import java.sql.Date;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClient(Client client);
    @Query("SELECT p FROM ItemPedido p JOIN FETCH p.pedido JOIN FETCH p.produto WHERE dataCompra BETWEEN :dataInicialSQL and :dataFinalSQL")
    List<ItemPedido> buscarItensPedidosInicioEFimMes(Date dataInicialSQL, Date dataFinalSQL);

    @Query("SELECT i.produto FROM ItemPedido i WHERE i.pedido.dataCompra BETWEEN :dataInicialSQL and :dataFinalSQL")
    List<Product> buscarProdutosEmPedidosIncioEFimMes(Date dataInicialSQL, Date dataFinalSQL);
}
