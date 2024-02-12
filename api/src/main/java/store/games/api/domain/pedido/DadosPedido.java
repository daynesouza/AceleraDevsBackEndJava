package store.games.api.domain.pedido;

import java.util.Date;
import java.util.List;

public record DadosPedido(
        String cpf,
        Date data,
        List<ListaItens> listaItens
){}
