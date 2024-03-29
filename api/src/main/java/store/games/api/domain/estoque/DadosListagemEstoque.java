package store.games.api.domain.estoque;
import java.util.Date;

public record DadosListagemEstoque(
        String produtoCod,
        int quantidade,
        Date data
){
    public DadosListagemEstoque(Estoque estoque){
        this(
                estoque.getProduto().getCod(),
                estoque.getQuantidade(),
                estoque.getData()
        );
    }
}
