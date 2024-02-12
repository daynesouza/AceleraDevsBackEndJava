package store.games.api.domain.itemPedido;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.games.api.domain.pedido.Pedido;
import store.games.api.domain.product.Product;

import java.math.BigDecimal;

@Table(name = "item_pedido")
@Entity(name = "ItemPedido")
@Getter
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product produto;

    private int quantidade;

    private BigDecimal valor;

    public ItemPedido(int quantidade, Pedido pedido, Product produto){
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.valor = produto.getPreco();
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
