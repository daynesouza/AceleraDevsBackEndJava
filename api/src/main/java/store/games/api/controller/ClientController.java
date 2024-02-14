package store.games.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import store.games.api.domain.client.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private ListarTodosDadosCliente listarDadosCliente;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody List<DadosCadastroCliente> dados, UriComponentsBuilder uriBuilder){
        dados.forEach( cliente -> repository.save(new Client(cliente)));

        var uri = uriBuilder.path("/produto/cadastro").build().toUri();

        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(Pageable paginacao){
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosCadastroCliente dados){
        var client = repository.getReferenceById(dados.cpf());
        client.atualizarDados(dados);

        return ResponseEntity.ok(new DadosCadastroCliente(client));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity desabilitar(@PathVariable String cpf){
        var client = repository.getReferenceById(cpf);
        client.atualizarStatus(false);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<TodosDadosCliente> detalhar(@PathVariable String cpf){
        var dados = listarDadosCliente.localizaCliente(cpf);

        return ResponseEntity.ok(dados);
    }
}
