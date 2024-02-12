package store.games.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import store.games.api.domain.estoque.DadosCadastroEstoque;
import store.games.api.domain.estoque.DadosListagemEstoque;
import store.games.api.domain.estoque.Estoque;
import store.games.api.domain.estoque.EstoqueRepository;
import store.games.api.domain.product.ProductRepository;
import store.games.api.infra.ValidacaoException;

import java.util.List;

@RestController
@RequestMapping("estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProductRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody List<DadosCadastroEstoque> dados, UriComponentsBuilder uriBuilder){

        dados.forEach( item -> {
            if(!produtoRepository.existsById(item.cod())){
                throw new ValidacaoException("COD do produto não foi localizado");
            }
            var produto = produtoRepository.findById(item.cod()).get();
            estoqueRepository.save( new Estoque(produto, item.quantidade(), item.data()));
        });

        var uri = uriBuilder.path("/estoque/cadastro").build().toUri();

        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemEstoque>> listar(Pageable paginacao){
        var page = estoqueRepository.findAll(paginacao).map(DadosListagemEstoque::new);

        return ResponseEntity.ok(page);
    }
}
