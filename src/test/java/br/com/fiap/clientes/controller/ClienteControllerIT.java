package br.com.fiap.clientes.controller;



import br.com.fiap.clientes.exception.ErrorMessage;
import br.com.fiap.clientes.web.dto.cliente.request.ClienteRequestDto;
import br.com.fiap.clientes.web.dto.cliente.response.ClienteResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import util.TestsUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClienteControllerIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarCliente_ComDadosValidos_RetornarClienteComStatus201(){

        ClienteResponseDto responseBody  = testClient
                .post()
                .uri("api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(TestsUtils.gerarMassaValidaParaClienteRequestDto())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ClienteResponseDto.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals("Jose Pereira", responseBody.getNome());
        assertEquals("jose.pereira@gmail.com", responseBody.getEmail());
        assertEquals("94655744065", responseBody.getCpf());
    }


    @Test
    public void criarCliente_ComCpfJaCadastrado_RetornarErrorMessageStatus409() {

        ClienteRequestDto requestDto  = TestsUtils.gerarMassaValidaParaClienteRequestDto();
        requestDto.setCpf("37347795028");

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals(409, responseBody.getStatus());
    }

    @Test
    public void criarCliente_ComDadosInvalidos_RetornarErrorMessageStatus422() {

        ClienteRequestDto requestDto1  = TestsUtils.gerarMassaValidaParaClienteRequestDto();
        requestDto1.setCpf("37347795088");

        ClienteRequestDto requestDto2  = TestsUtils.gerarMassaValidaParaClienteRequestDto();
        requestDto2.setTelefone("0000000");

        ClienteRequestDto requestDto3  = TestsUtils.gerarMassaValidaParaClienteRequestDto();
        requestDto3.setEnderecos(null);

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto1)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals(422, responseBody.getStatus());

        responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto2)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals(422, responseBody.getStatus());

        responseBody = testClient
                .post()
                .uri("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto3)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals(422, responseBody.getStatus());

    }



    @Test
    public void buscarCliente_ComCpfExistente_RetornarClienteComStatus200() {
        ClienteResponseDto responseBody = testClient
                .get()
                .uri("/api/v1/clientes/37347795028")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ClienteResponseDto.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals("37347795028", responseBody.getCpf());
    }

    @Test
    public void buscarCliente_ComCpfInexistente_RetornarClienteComStatus404() {
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/clientes/41581427093")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertEquals(404, responseBody.getStatus());
    }

}
