package br.com.fiap.clientes.repository;


import br.com.fiap.clientes.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClienteRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testCreateCliente() {
        // Cria um cliente de exemplo
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("999999999");
        cliente.setCpf("37347795028");

        // Persiste o cliente no banco de dados em memória
        Cliente savedCliente = entityManager.persistFlushFind(cliente);

        // Verifica se o cliente foi persistido corretamente
        assertNotNull(savedCliente);
        assertNotNull(savedCliente.getId());
        assertEquals(cliente.getNome(), savedCliente.getNome());
        assertEquals(cliente.getCpf(), savedCliente.getCpf());
    }

    @Test
    public void testFindByCpf() {
        // Cria um cliente de exemplo e persiste
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria.oliveira@example.com");
        cliente.setTelefone("888888888");
        cliente.setCpf("98765432109");

        entityManager.persistAndFlush(cliente);

        // Busca por CPF
        Optional<Cliente> foundCliente = clienteRepository.findByCpf("98765432109");

        // Verifica se o cliente foi encontrado e os dados estão corretos
        assertTrue(foundCliente.isPresent());
        assertEquals(cliente.getNome(), foundCliente.get().getNome());
        assertEquals(cliente.getCpf(), foundCliente.get().getCpf());

        // Testa a busca por um CPF que não existe
        Optional<Cliente> notFoundCliente = clienteRepository.findByCpf("00000000000");
        assertTrue(notFoundCliente.isEmpty());
    }
}
