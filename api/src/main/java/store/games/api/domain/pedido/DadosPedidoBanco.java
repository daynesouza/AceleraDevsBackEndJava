package store.games.api.domain.pedido;

import store.games.api.domain.client.Client;
import store.games.api.domain.itemPedido.ItemPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record DadosPedidoBanco(
        Long id,
        Client client,
        List<ItemPedido> itens,
        Date dataCompra,
        BigDecimal valorTotal
){}
