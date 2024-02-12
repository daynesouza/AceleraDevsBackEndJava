package store.games.api.domain.client;

import java.util.Date;

public record DadosListagemCliente(String nome, String CPF, Date dta) {

    public DadosListagemCliente(Client client){

        this(
                client.getName(),
                client.getCpf(),
                client.getDta()
        );
    }
}
