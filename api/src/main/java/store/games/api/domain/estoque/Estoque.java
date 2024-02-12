package store.games.api.domain.estoque;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.games.api.domain.product.Product;

import java.util.Date;

@Table(name = "estoque")
@Entity(name = "estoque")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_cod")
    private Product produto;

    @Column(name = "data_referencia")
    private Date data;

    private int quantidade;

    public Estoque( Product produto, int quantidade, Date data){
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
    }
}
