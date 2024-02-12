package store.games.api.domain.pedido;

import store.games.api.domain.itemPedido.DadosListagemItemPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record DadosListagemPedido(
        long id,
        String cpf,
        Date dataCompra,
        List<DadosListagemItemPedido> itens,
        BigDecimal valorTotal
){
    public DadosListagemPedido(Pedido pedido){
        this(
                pedido.getId(),
                pedido.getClient().getCpf(),
                pedido.getDataCompra(),
                pedido.listaItemPedido(),
                pedido.getValorTotal());
    }
}

