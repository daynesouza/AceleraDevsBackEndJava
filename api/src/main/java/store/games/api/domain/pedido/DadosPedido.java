package store.games.api.domain.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public record DadosPedido(
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
        Date data,
        List<ListaItens> listaItens
){}
