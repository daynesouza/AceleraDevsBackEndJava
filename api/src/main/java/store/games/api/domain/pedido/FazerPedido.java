package store.games.api.domain.pedido;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.games.api.domain.ValidacaoException;
import store.games.api.domain.client.ClientRepository;
import store.games.api.domain.itemPedido.ItemPedido;
import store.games.api.domain.product.ProductRepository;

import java.util.List;
@Service
public class FazerPedido {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public void cadastrarDadosPedido(DadosPedido dados){
        if (!clientRepository.existsById(dados.cpf())){
            throw new ValidacaoException("CPF do usuário não está cadastrado!");
        }

        var cliente = clientRepository.getReferenceById(dados.cpf());
        var pedido = new Pedido(cliente, dados.data());
        pedidoRepository.save(pedido);
        adicionarItemPedido(dados.listaItens(), pedido);
    }

    public void adicionarItemPedido(List<ListaItens> itens, Pedido pedido){
        itens.forEach(item -> {
            //Perguntar o motivo do produto existindo na base, o retorno ser "false"
//            if (!clientRepository.existsById(item.cod())){
//                throw new ValidacaoException("COD do produto não está cadastrado!");
//            }

            var produto = productRepository.findById(item.cod()).get();
            pedido.adicionarItem( new ItemPedido(item.quantidade(), pedido, produto));
        });
    }
}
