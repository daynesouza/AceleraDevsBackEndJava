package store.games.api.domain.itemPedido;

import store.games.api.domain.pedido.Pedido;
import store.games.api.domain.product.Product;

import java.math.BigDecimal;

public record DadosItemPedido(
        Long id,
        Pedido pedido,
        Product produto,
        int quantidade,
        BigDecimal valor
){
}
