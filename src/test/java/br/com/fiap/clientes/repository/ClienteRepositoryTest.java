package br.com.fiap.clientes.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.fiap.clientes.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ClienteRepositoryTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testCreateCliente() {
        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(novoCliente);

        Cliente savedCliente = clienteRepository.save(novoCliente);

        assertNotNull(savedCliente);
        assertEquals("João Silva", savedCliente.getNome());
        assertEquals("37347795028", savedCliente.getCpf());
    }

    @Test
    public void testFindByCpf() {
        Optional<Cliente> expectedCliente = Optional.of(new Cliente(1L, "Maria Oliveira", "maria@example.com", "888888888", "98765432109", null));
        when(clienteRepository.findByCpf("98765432109")).thenReturn(expectedCliente);

        Optional<Cliente> found = clienteRepository.findByCpf("98765432109");

        assertTrue(found.isPresent());
        assertEquals("Maria Oliveira", found.get().getNome());
        assertEquals("98765432109", found.get().getCpf());

        // Testar com um CPF que não existe
        when(clienteRepository.findByCpf("00000000000")).thenReturn(Optional.empty());
        Optional<Cliente> notFound = clienteRepository.findByCpf("00000000000");

        assertTrue(notFound.isEmpty());
    }
}

