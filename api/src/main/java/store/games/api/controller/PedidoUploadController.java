package store.games.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import store.games.api.domain.pedido.CadastrarPlanilhaDePedidos;
import store.games.api.domain.pedido.DadosPedidoRetorno;
import store.games.api.domain.pedido.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pedido-upload")
public class PedidoUploadController {
    @Autowired
    private CadastrarPlanilhaDePedidos cadastroPedido;

    @Autowired
    private PedidoRepository repository;

    @PostMapping
    public ResponseEntity cadastroCompra(@RequestParam MultipartFile xlsx, UriComponentsBuilder uriBuilder){
        List<DadosPedidoRetorno> pedidoRetorno = new ArrayList<>();
        pedidoRetorno = cadastroPedido.cadastrarPedidoPlanilha(xlsx);

        var uri = uriBuilder.path("/pedido-upload/cadastro").build().toUri();

        return ResponseEntity.created(uri).body(pedidoRetorno);
    }
}
