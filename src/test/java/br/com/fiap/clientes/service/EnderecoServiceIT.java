package br.com.fiap.clientes.service;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class EnderecoServiceIT {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCreateEndereco() {
        Cliente cliente = clienteService.buscarPorCpf("37347795028");

        // Valida se o cliente não está null
        assertNotNull(cliente);

        Endereco endereco = new Endereco();

        endereco.setCliente(cliente);
        endereco.setId(10L);
        endereco.setRua("Rua Sanazar Mardiros, 508");
        endereco.setCep("06210-00");
        endereco.setEstado("SP");
        endereco.setCidade("Osasco");

        Endereco savedEndereco = enderecoService.salvarEndereco(endereco);

        //Valida endereço inserido na base
        assertNotNull(savedEndereco);
        assertNotNull(savedEndereco.getId());
        assertEquals(endereco.getCep(), savedEndereco.getCep());
        assertEquals(endereco.getRua(), savedEndereco.getRua());
    }

    @Test
    public void testRetrieveEnderecoByIdSuccessfully() {
        Endereco endereco = enderecoService.buscarPorId(10L);


        //Valida endereço inserido na base
        assertNotNull(endereco);
        assertNotNull(endereco.getId());
        assertEquals("01002030", endereco.getCep());
        assertEquals("Rua das Flores", endereco.getRua());
    }

    @Test
    public void testShouldNotFindEnderecoByNonexistentId() {


        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            enderecoService.buscarPorId(9L);
        });

        assertEquals("Endereço id=9 não encontrado", thrown.getMessage(),
                "A mensagem da exceção não é a esperada.");

    }

    @Test
    public void testUpdateEndereco() {

        Endereco endereco = new Endereco();

        endereco.setRua("Rua Sanazar Mardiros, 508");
        endereco.setCep("06210-00");
        endereco.setEstado("SP");
        endereco.setCidade("Osasco");

        enderecoService.atualizarEndereco(endereco,10L);
        Endereco RetrievedEndereco = enderecoService.buscarPorId(10L);

        assertNotNull(RetrievedEndereco);
        assertEquals(endereco.getCep(), RetrievedEndereco.getCep());
        assertEquals(endereco.getRua(), RetrievedEndereco.getRua());
        assertEquals(endereco.getEstado(), RetrievedEndereco.getEstado());
        assertEquals(endereco.getCidade(), RetrievedEndereco.getCidade());

    }

}
