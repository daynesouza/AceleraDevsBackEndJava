package store.games.api.domain.pedido;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CadastrarPlanilhaDePedidos {
    @Autowired
    FazerPedido fazerPedido;
    public List<DadosPedidoRetorno> cadastrarPedidoPlanilha(MultipartFile xlsx){
        List<DadosPedidoRetorno> pedidosRetorno = new ArrayList<>();

        try{
            //Recuperando o arquivo
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(xlsx.getOriginalFilename()).toFile();
            xlsx.transferTo(tempFile);
            Workbook workbook = WorkbookFactory.create(tempFile);

            //Setando a aba
            Sheet sheet = workbook.getSheetAt(0);

            //Convertendo as linhas em lista
            List<Row> rows = (List<Row>) converterLista(sheet.iterator());

            //Removendo a primeira linha (os cabeçalhos)
            rows.remove(0);

            rows.forEach( row -> {
                //Setando as célula
                List<Cell> cells = (List<Cell>) converterLista(row.cellIterator());

                var codigoProduto = cells.get(0).getStringCellValue();
                var cpfUsuario = String.valueOf( (int) cells.get(1).getNumericCellValue());
                var quantidade = (int) cells.get(2).getNumericCellValue();
                var dataPlan = cells.get(3).getDateCellValue();
                var listaitens = new ArrayList<ListaItens>();
                listaitens.add( new ListaItens(codigoProduto, quantidade));

                //Convertendo Date para LocalDate
                var data = dataPlan.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();

                pedidosRetorno.add(fazerPedido.cadastrarDadosPedido( new DadosPedido(cpfUsuario, data, listaitens)));

            });

        }catch (IOException e) {
            e.printStackTrace();
        }

        return pedidosRetorno;
    }

    public List<?> converterLista(Iterator<?> interator){
        return IteratorUtils.toList(interator);
    }
}
