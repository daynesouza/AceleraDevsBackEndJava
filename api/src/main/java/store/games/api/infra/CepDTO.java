package store.games.api.infra;

public record CepDTO(
        String cep,
        String logradouro,
        String complemento,
        String localidade,
        String uf
){}
