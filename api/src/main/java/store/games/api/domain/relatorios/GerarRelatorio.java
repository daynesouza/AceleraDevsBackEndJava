package store.games.api.domain.relatorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.games.api.domain.estoque.DadosListagemEstoque;
import store.games.api.domain.estoque.EstoqueRepository;
import store.games.api.domain.itemPedido.ItemPedido;
import store.games.api.domain.pedido.PedidoRepository;
import store.games.api.domain.product.Product;
import store.games.api.domain.product.ProductRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GerarRelatorio {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<RelatorioProdutosVendidosDTO> relatorioFinalProdutos(DataJson data){
        List<RelatorioProdutosVendidos> listaRelatorio = new ArrayList<>();
        List<RelatorioProdutosVendidosDTO> relatorioDTO = new ArrayList<>();
        List<Product> produtosVendidos;

        //Formatando a data e separando a data inicial da data final
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = data.date();
        int diferencaDias = dataInicial.lengthOfMonth();
        LocalDate dataFinal = dataInicial.plusDays(diferencaDias-1);

        //Convertendo localDate para SQL DATE
        var dataInicialSQL = Date.valueOf(dataInicial);
        var dataFinalSQL = Date.valueOf(dataFinal);

        produtosVendidos = pedidoRepository.buscarProdutosEmPedidosIncioEFimMes(dataInicialSQL, dataFinalSQL);
        List<ItemPedido> listItensPedidos = pedidoRepository.buscarItensPedidosInicioEFimMes(dataInicialSQL, dataFinalSQL);
        List<DadosListagemEstoque> estoqueDisponivel = estoqueRepository.buscarEstoqueInicioEFimMes(dataInicialSQL, dataFinalSQL);

        for (Product produto:produtosVendidos){
            var quantidade = 0;
            int estoque = 0;
            String status = "QTD_DIVERGENTE";
            List<PedidosRealizados> pedidosRealizados = new ArrayList<>();

            for(ItemPedido itemPedido: listItensPedidos){
                if(itemPedido.getProduto().getCod() == produto.getCod()){
                    quantidade = quantidade + itemPedido.getQuantidade();
                    pedidosRealizados.add( new PedidosRealizados(
                            itemPedido.getPedido().getClient().getName(),
                            itemPedido.getPedido().getClient().getCpf(),
                            itemPedido.getPedido().getDataCompra(),
                            itemPedido.getQuantidade()));

                }
            }
            listaRelatorio.add(new RelatorioProdutosVendidos(produto, quantidade, estoque, status, pedidosRealizados));
        }

        //Atribuindo estoque e verificando o status das vendas
        estoqueDisponivel.forEach( est -> {
            listaRelatorio.forEach( item -> {
                if(est.produtoCod() == item.getProduto().getCod()){
                    item.setEstoque(est.quantidade());
                    if( item.getQuantidadeTotalVendida() / item.getEstoque() < 25){
                        item.setStatus("BAIXA_DEMANDA");
                    } else if( item.getEstoque() < item.getQuantidadeTotalVendida() ){
                        item.setStatus("QTD_DIVERGENTE");
                    } else{
                        item.setStatus("OK");
                    }
                }
            });
        });

        //Convertendo os dados do relatório para o DTO
        listaRelatorio.forEach(relatorio -> relatorioDTO.add(new RelatorioProdutosVendidosDTO(relatorio)));

        return relatorioDTO;
    }
}
