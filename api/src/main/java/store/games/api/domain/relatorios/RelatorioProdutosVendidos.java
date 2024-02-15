package store.games.api.domain.relatorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import store.games.api.domain.product.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RelatorioProdutosVendidos {
    private Product produto;
    private int quantidadeTotalVendida;
    private int estoque;
    private String status;
    private List<PedidosRealizados> pedidosRealizados;
}
