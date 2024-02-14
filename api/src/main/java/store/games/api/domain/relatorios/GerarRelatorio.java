package store.games.api.domain.relatorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.games.api.domain.pedido.DadosPedidoBanco;
import store.games.api.domain.pedido.PedidoRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GerarRelatorio {
    @Autowired
    private PedidoRepository pedidoRepository;

    public RelatorioFinalProdutos relatorioFinalProdutos(DataJson data){
        //Formatando a data e separando a data inicial da data final
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = data.date();
        int diferencaDias = dataInicial.lengthOfMonth();
        LocalDate dataFinal = dataInicial.plusDays(diferencaDias-1);

        //Convertendo localDate para SQL DATE
        var dataInicialSQL = Date.valueOf(dataInicial);
        var dataFinalSQL = Date.valueOf(dataFinal);

        List<DadosPedidoBanco> pedidos = pedidoRepository.buscarPedidosInicioEFimMes(dataInicialSQL, dataFinalSQL);

        System.out.println(pedidos);
        System.out.println("oh nois");
        pedidos.forEach( pedido -> System.out.println(pedido.id()));

        return null;
    }
}
