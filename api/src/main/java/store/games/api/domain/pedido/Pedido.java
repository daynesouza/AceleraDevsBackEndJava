package store.games.api.domain.pedido;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.games.api.domain.client.Client;
import store.games.api.domain.itemPedido.DadosListagemItemPedido;
import store.games.api.domain.itemPedido.ItemPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "client_cpf")
    private Client client;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "data_compra")
    private Date dataCompra;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    public Pedido( Client cliente, Date dataCompra){
        this.client = cliente;
        this.dataCompra = dataCompra;
    }

    //Perguntar a boa prática nesse caso abaixo
    public List<DadosListagemItemPedido> listaItemPedido(){
        List<DadosListagemItemPedido> listaDadosListagemItemPedido = new ArrayList<>();
        this.itens.forEach( item -> {
            listaDadosListagemItemPedido.add(new DadosListagemItemPedido (item.getProduto().getName(), item.getQuantidade(), item.getValor()));
        });

        return listaDadosListagemItemPedido;
    }
    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(
                item.getValor().multiply(BigDecimal.valueOf(item.getQuantidade()))
        );
    }
}
