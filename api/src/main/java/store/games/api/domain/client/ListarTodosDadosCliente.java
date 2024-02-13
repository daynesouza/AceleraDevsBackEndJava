package store.games.api.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.games.api.domain.itemPedido.DadosListagemItemPedido;
import store.games.api.domain.pedido.DadosListagemPedido;
import store.games.api.domain.pedido.PedidoRepository;
import store.games.api.infra.APIService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarTodosDadosCliente {
    @Autowired
    private ClientRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private APIService api;

    public TodosDadosCliente localizaCliente(String cpf){
        List<DadosListagemItemPedido> produtosComprados = new ArrayList<>();
        List<DadosListagemPedido> pedidos = new ArrayList<>();

        var client = clienteRepository.findById(cpf).get();

        pedidos = pedidoRepository.findByClient(client).stream().map(DadosListagemPedido::new).toList();

        var endereco = api.pesquisarCEP(client.getCep());

        return new TodosDadosCliente(client, endereco, pedidos);

    }
}
