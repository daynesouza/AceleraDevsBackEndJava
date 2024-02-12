package store.games.api.domain.itemPedido;

import java.math.BigDecimal;

public record DadosListagemItemPedido(
        String produto,
        int quantidade,
        BigDecimal valor
){}
