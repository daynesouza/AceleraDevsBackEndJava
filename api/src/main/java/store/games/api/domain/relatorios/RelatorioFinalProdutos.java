package store.games.api.domain.relatorios;

import java.util.List;

public record RelatorioFinalProdutos(
        String qtd_divergente,
        List<RelatorioProdutosVendidos> relatorioProdutosVendidosList
){}
