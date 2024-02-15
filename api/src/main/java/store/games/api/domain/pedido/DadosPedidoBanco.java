package store.games.api.domain.pedido;

import store.games.api.domain.client.Client;
import store.games.api.domain.itemPedido.DadosListagemItemPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record DadosPedidoBanco(
        Long id,
        Client client,
        List<DadosListagemItemPedido> itens,
        Date dataCompra,
        BigDecimal valorTotal
){
    public DadosPedidoBanco(Pedido pedido){
        this(
                pedido.getId(),
                pedido.getClient(),
                pedido.listaItemPedido(),
                pedido.getDataCompra(),
                pedido.getValorTotal()
        );
    }
}
