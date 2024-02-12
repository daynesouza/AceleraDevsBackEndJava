package store.games.api.domain.product;

import java.math.BigDecimal;

public record DadosListagemProduto(String name, BigDecimal preco, String cod) {

    public DadosListagemProduto(Product product) {
        this(
                product.getName(),
                product.getPreco(),
                product.getCod()
        );
    }
}
