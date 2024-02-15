package store.games.api.domain.pedido;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.games.api.infra.ValidacaoException;
import store.games.api.domain.client.ClientRepository;
import store.games.api.domain.itemPedido.ItemPedido;
import store.games.api.domain.product.ProductRepository;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    public DadosPedidoRetorno cadastrarDadosPedido(DadosPedido dados){
        if (!clientRepository.existsById(dados.cpf())){
            throw new ValidacaoException("CPF do usuário não está cadastrado!");
        }
        //convertendo de Localdate para data
        var data = Date.from(dados.data().atStartOfDay(ZoneId.systemDefault()).toInstant());

        var cliente = clientRepository.getReferenceById(dados.cpf());
        var pedido = new Pedido(cliente, data);
        pedidoRepository.save(pedido);
        adicionarItemPedido(dados.listaItens(), pedido);

        return new DadosPedidoRetorno(cliente.getName(), pedido.getValorTotal(), data);
    }


    public void adicionarItemPedido(List<ListaItens> itens, Pedido pedido){
        itens.forEach(item -> {
            if (!productRepository.existsById(item.cod())){
                throw new ValidacaoException("COD do produto não está cadastrado!");
            }

            var produto = productRepository.findById(item.cod()).get();
            pedido.adicionarItem( new ItemPedido(item.quantidade(), pedido, produto));
        });
    }
}
