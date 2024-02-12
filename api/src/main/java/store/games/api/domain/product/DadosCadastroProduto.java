package store.games.api.domain.product;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        String cod,
        String name,
        BigDecimal preco) {
}
