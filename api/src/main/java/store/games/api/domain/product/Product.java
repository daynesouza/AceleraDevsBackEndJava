package store.games.api.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Table(name = "Products")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "cod")
public class Product{

    @Id
    private String cod;
    private String name;
    @Column(name = "price")
    private BigDecimal preco;
    private Boolean status;

    public Product(DadosCadastroProduto dados){
        this.cod = dados.cod();
        this.name = dados.name();
        this.preco = dados.preco();
        this.status = true;
    }

    public void atualizaDados(DadosCadastroProduto dados) {
        if(dados.name() != null){
            this.name = dados.name();
        }
        if(dados.preco() != null){
            this.preco = dados.preco();
        }
    }

    public void atualizarStatus(boolean status) {
        this.status = status;
    }
}
