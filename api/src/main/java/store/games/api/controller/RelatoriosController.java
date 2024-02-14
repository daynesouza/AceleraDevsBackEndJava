package store.games.api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.games.api.domain.relatorios.DataJson;
import store.games.api.domain.relatorios.GerarRelatorio;
import store.games.api.domain.relatorios.RelatorioProdutosVendidos;

import java.util.Date;

@RestController
@RequestMapping("relatorios")
public class RelatoriosController {
    @Autowired
    GerarRelatorio gerarRelatorio;

    @GetMapping
    public ResponseEntity<Page<RelatorioProdutosVendidos>> lista(@RequestBody DataJson date, Pageable paginacao){
        gerarRelatorio.relatorioFinalProdutos(date);

        return null;
    }
}
