package br.com.fiap.clientes.service;


import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.exception.CpfUniqueViolationException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class ClienteServiceIT {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCreateCliente() {
        // Cria um cliente de exemplo
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("999999999");
        cliente.setCpf("12345678911");

        // Persiste o cliente no banco de dados em memória
        Cliente savedCliente = clienteService.salvar(cliente);

        // Verifica se o cliente foi persistido corretamente
        assertNotNull(savedCliente);
        assertNotNull(savedCliente.getId());
        assertEquals(cliente.getNome(), savedCliente.getNome());
        assertEquals(cliente.getCpf(), savedCliente.getCpf());
    }


    @Test
    public void testClienteCreationFailsOnCpfConflict() {
        // Cria um cliente de exemplo
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("999999999");
        cliente.setCpf("37347795028");


        assertThrows(CpfUniqueViolationException.class, () -> {
            clienteService.salvar(cliente);
        });

    }

    @Test
    public void testRetrieveClienteByCpfSuccessfully() {

        Cliente clienteRetrieve = clienteService.buscarPorCpf("37347795028");

        assertNotNull(clienteRetrieve);
        assertEquals("João Silva",clienteRetrieve.getNome());
        assertEquals("joao.silva@example.com", clienteRetrieve.getEmail());

    }

    @Test
    public void testShouldNotFindClienteByNonexistentCpf() {

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            clienteService.buscarPorCpf("00000000001");
        });

        assertEquals("Cliente com CPF '00000000001' não encontrado", thrown.getMessage(),
                "A mensagem da exceção não é a esperada.");

    }
}
