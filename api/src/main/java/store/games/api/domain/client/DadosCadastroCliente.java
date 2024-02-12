package store.games.api.domain.client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record DadosCadastroCliente(
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
        Date dta,
        String cpf,
        String cep) {

        public DadosCadastroCliente(Client client){
                this( client.getName(), client.getDta(), client.getCpf(), client.getCep());
        }
}
