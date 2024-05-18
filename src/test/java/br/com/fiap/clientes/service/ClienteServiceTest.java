package br.com.fiap.clientes.service;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.exception.CpfUniqueViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServiceTest {

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testCreateCliente() {
        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);
        when(clienteService.salvar(any(Cliente.class))).thenReturn(novoCliente);

        Cliente salvoCliente = clienteService.salvar(novoCliente);

        assertNotNull(salvoCliente);
        assertEquals(novoCliente.getNome(), salvoCliente.getNome());
        assertEquals(novoCliente.getEmail(), salvoCliente.getEmail());
        assertEquals(novoCliente.getTelefone(), salvoCliente.getTelefone());
        assertEquals(novoCliente.getCpf(), salvoCliente.getCpf());

    }

    @Test
    public void testBuscarCliente() {

        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);
        when(clienteService.salvar(any(Cliente.class))).thenReturn(novoCliente);

        Cliente salvoCliente = clienteService.salvar(novoCliente);
        assertNotNull(salvoCliente);


        when(clienteService.buscarPorCpf(any(String.class))).thenReturn(novoCliente);
        Cliente clienteBuscado = clienteService.buscarPorCpf(salvoCliente.getCpf());

        assertNotNull(clienteBuscado);
        assertEquals(novoCliente.getCpf(), clienteBuscado.getCpf());
        assertEquals(novoCliente.getNome(), clienteBuscado.getNome());
        assertEquals(novoCliente.getEmail(), clienteBuscado.getEmail());
        assertEquals(novoCliente.getTelefone(), clienteBuscado.getTelefone());
        assertEquals(novoCliente.getCpf(), clienteBuscado.getCpf());

    }

}
