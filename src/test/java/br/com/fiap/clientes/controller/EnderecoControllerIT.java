package br.com.fiap.clientes.controller;

import br.com.fiap.clientes.exception.ErrorMessage;
import br.com.fiap.clientes.web.dto.endereco.request.EnderecoRequestDTO;
import br.com.fiap.clientes.web.dto.endereco.response.EnderecoResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EnderecoControllerIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarEndereco_ComDadosValidos_RetornarEnderecoComStatus201() {

        EnderecoRequestDTO requestDto = new EnderecoRequestDTO("Rua Nova", "Cidade Nova", "Estado Novo", "12345-678", "37347795028");
        EnderecoResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EnderecoResponseDto.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals("Rua Nova", responseBody.getRua());
        assertEquals("Cidade Nova", responseBody.getCidade());
        assertEquals("Estado Novo", responseBody.getEstado());
        assertEquals("12345-678", responseBody.getCep());
    }

    @Test
    public void atualizarEndereco_ComDadosValidos_RetornarStatus204() {
        EnderecoRequestDTO requestDto = new EnderecoRequestDTO("Rua Atualizada", "Cidade Atual", "Estado Atual", "98765-432", "37347795028");

        testClient
                .patch()
                .uri("/api/v1/enderecos/10")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void criarEndereco_DadosIncompletos_RetornarErroDeValidacao() {
        EnderecoRequestDTO requestDto = new EnderecoRequestDTO("", "", "", "12345-678", "12345678909"); // Campos de endereço faltando

        testClient
                .post()
                .uri("/api/v1/enderecos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .consumeWith(response -> {
                    ErrorMessage errorMessage = response.getResponseBody();
                    assertNotNull(errorMessage);
                    assertEquals("Campo(s) invalido(s)", errorMessage.getMessage());
                });
    }

    // Testar a atualização de um endereço que não existe
    @Test
    public void atualizarEndereco_EnderecoNaoExistente_RetornarNotFound() {
        EnderecoRequestDTO requestDto = new EnderecoRequestDTO("Rua Nova", "Cidade Nova", "Estado Novo", "12345-678", "12345678909");

        testClient
                .patch()
                .uri("/api/v1/enderecos/9999") // ID que presumivelmente não existe
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .consumeWith(response -> {
                    ErrorMessage errorMessage = response.getResponseBody();
                    assertNotNull(errorMessage);
                    assertEquals("Endereço id=9999 não encontrado", errorMessage.getMessage());
                });
    }
}
