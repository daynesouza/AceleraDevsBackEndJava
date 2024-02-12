package store.games.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import store.games.api.domain.product.DadosCadastroProduto;
import store.games.api.domain.product.DadosListagemProduto;
import store.games.api.domain.product.Product;
import store.games.api.domain.product.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController{

    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody List<DadosCadastroProduto> dados, UriComponentsBuilder uriBuilder){
        dados.forEach( produto -> repository.save(new Product(produto)));

        var uri = uriBuilder.path("product/cadastro").build().toUri();

        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(Pageable paginacao){

        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemProduto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosCadastroProduto dados){
        var product = repository.getReferenceById(dados.cod());
        product.atualizaDados(dados);

        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{cod}")
    @Transactional
    public ResponseEntity desabilitar(@PathVariable String cod){
        var product = repository.getReferenceById(cod);
        product.atualizarStatus(false);

        return ResponseEntity.noContent().build();
    }

}
