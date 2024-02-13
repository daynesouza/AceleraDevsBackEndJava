package store.games.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import store.games.api.domain.pedido.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {
    @Autowired
    private FazerPedido fazerPedido;

    @Autowired
    private PedidoRepository repository;

    @PostMapping
    public ResponseEntity cadastroCompra(@RequestBody List<DadosPedido> dados, UriComponentsBuilder uriBuilder){
        List<DadosPedidoRetorno> pedidoRetorno = new ArrayList<>();

        dados.forEach( pedido -> {
            pedidoRetorno.add(fazerPedido.cadastrarDadosPedido(pedido));
        });

        var uri = uriBuilder.path("/pedido/cadastro").build().toUri();

        return ResponseEntity.created(uri).body(pedidoRetorno);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPedido>> listar(Pageable paginacao){
        //Perguntar ao professor o porque do código dar erro de loop se retornar direto o objeto retornado pelo banco, no lugar do DTO.
        var page = repository.findAll(paginacao).map(DadosListagemPedido::new);
        return ResponseEntity.ok(page);
    }
}
