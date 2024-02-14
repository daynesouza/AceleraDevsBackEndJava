package store.games.api.domain.relatorios;

import java.util.Date;

public record PedidosRealizados(
        String nome,
        String cpf,
        Date data,
        int quantidade
){}
