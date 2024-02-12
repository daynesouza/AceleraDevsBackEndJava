package store.games.api.domain.client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "clients")
@Entity(name = "Client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "cpf")
public class Client {

    @Id
    private String cpf;
    private String name;
    private Date dta;
    private String cep;
    private Boolean status;

    public Client(DadosCadastroCliente dados) {
        this.cpf = dados.cpf();
        this.name = dados.name();
        this.dta = dados.dta();
        this.cep = dados.cep();
        this.status = true;
    }

    public void atualizarDados(DadosCadastroCliente dados) {
        if(dados.name() != null){
            this.name = dados.name();
        }
        if(dados.dta() != null){
            this.dta = dados.dta();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
    }

    public void atualizarStatus(boolean status) {
        this.status = status;
    }
}
