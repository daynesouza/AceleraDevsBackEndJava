package store.games.api.domain.relatorios;

import lombok.Setter;
import store.games.api.domain.product.Product;

import java.util.List;
public record RelatorioProdutosVendidosDTO(
        Product produto,
        int quantidadeTotalVendida,
        int estoque,
        String status,
        List<PedidosRealizados> pedidosRealizados

){
    public RelatorioProdutosVendidosDTO(RelatorioProdutosVendidos relatorio) {
        this(
                relatorio.getProduto(),
                relatorio.getQuantidadeTotalVendida(),
                relatorio.getEstoque(),
                relatorio.getStatus(),
                relatorio.getPedidosRealizados()
        );
    }
}