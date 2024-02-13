package store.games.api.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {
    @Autowired
    private RestTemplate restTemplate;

    public CepDTO pesquisarCEP (String cep){
        String url = "https://viacep.com.br/ws/"+ cep +"/json/";
        ResponseEntity<CepDTO> resp = restTemplate.getForEntity(url, CepDTO.class);

        return resp.getBody();
    }
}
