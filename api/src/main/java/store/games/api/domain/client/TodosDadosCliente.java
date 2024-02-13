package store.games.api.domain.client;

import store.games.api.domain.pedido.DadosListagemPedido;
import store.games.api.infra.CepDTO;

import java.util.List;

public record TodosDadosCliente(
   Client cliente,
   CepDTO endereco,
   List<DadosListagemPedido> pedidosRealizados
) {}
