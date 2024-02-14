package store.games.api.domain.relatorios;

import store.games.api.domain.client.Client;
import store.games.api.domain.product.Product;

import java.util.List;

public record RelatorioProdutosVendidos(
        Product produto,
        int quantidadeTotalVendida,
        List<PedidosRealizados> pedidosRealizados

){}