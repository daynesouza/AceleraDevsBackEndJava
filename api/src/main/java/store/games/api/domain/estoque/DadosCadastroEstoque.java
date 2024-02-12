package store.games.api.domain.estoque;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DadosCadastroEstoque(
        String cod,
        int quantidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
        Date data
){}
