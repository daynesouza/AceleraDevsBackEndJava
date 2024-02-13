package store.games.api.domain.pedido;

import java.math.BigDecimal;
import java.util.Date;

public record DadosPedidoRetorno(String nome, BigDecimal valor, Date data) {
}
