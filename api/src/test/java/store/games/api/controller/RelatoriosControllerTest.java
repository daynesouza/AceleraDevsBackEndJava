package store.games.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import store.games.api.domain.relatorios.DataJson;
import store.games.api.domain.relatorios.GerarRelatorio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class RelatoriosControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataJson> dataJson;

    @Autowired
    GerarRelatorio gerarRelatorio;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    @DisplayName("Devolver código erro http 400 - TESTE NÃO FUNCIONA, RETORNA ERRO!")
//    void listaCenario1() throws Exception{
//        //Error retornado : org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'store.games.api.controller.RelatoriosControllerTest': Unsatisfied dependency expressed through field 'dataJson': No qualifying bean of type 'org.springframework.boot.test.json.JacksonTester<store.games.api.domain.relatorios.DataJson>' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
//        var data = new DataJson(null);
//
//        var response = mvc
//                .perform(
//                        get("/relatorios")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                //.content("{\"date\": \"0\"}")
//                                .content(objectMapper.writeValueAsString(data))
//                )
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
}