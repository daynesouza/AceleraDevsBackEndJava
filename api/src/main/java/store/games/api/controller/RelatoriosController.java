package store.games.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.games.api.domain.estoque.DadosListagemEstoque;
import store.games.api.domain.relatorios.DataJson;
import store.games.api.domain.relatorios.GerarRelatorio;
import store.games.api.domain.relatorios.RelatorioProdutosVendidos;
import store.games.api.domain.relatorios.RelatorioProdutosVendidosDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("relatorios")
public class RelatoriosController {
    @Autowired
    GerarRelatorio gerarRelatorio;

    @GetMapping
    public ResponseEntity<List<RelatorioProdutosVendidosDTO>> lista(@RequestBody DataJson date){

        var relatorioDTO = gerarRelatorio.relatorioFinalProdutos(date);

        return ResponseEntity.ok(relatorioDTO);
    }
}
