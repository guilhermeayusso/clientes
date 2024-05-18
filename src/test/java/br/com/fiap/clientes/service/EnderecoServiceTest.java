package br.com.fiap.clientes.service;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EnderecoServiceTest {

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testCreateEndereco() {

        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);
        when(clienteService.salvar(any(Cliente.class))).thenReturn(novoCliente);

        Cliente salvoCliente = clienteService.salvar(novoCliente);

        when(clienteService.buscarPorCpf(any(String.class))).thenReturn(salvoCliente);
        Cliente clienteCpf = clienteService.buscarPorCpf("37347795028");

        Endereco savedEndereco = new Endereco(1L, "Sanazar Mardiros, 508", "Osasco",
                "SP", "06210-000", clienteCpf);

        when(enderecoService.salvarEndereco(any(Endereco.class))).thenReturn(savedEndereco);

        savedEndereco = enderecoService.salvarEndereco(savedEndereco);

        assertNotNull(savedEndereco);
        assertEquals("06210-000", savedEndereco.getCep());
        assertEquals("Osasco", savedEndereco.getCidade());
        verify(enderecoService, times(1)).salvarEndereco(savedEndereco);

    }

    @Test
    public void testFoundEnderecoSuccess() {

        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);
        when(clienteService.salvar(any(Cliente.class))).thenReturn(novoCliente);

        Cliente salvoCliente = clienteService.salvar(novoCliente);

        when(clienteService.buscarPorCpf(any(String.class))).thenReturn(salvoCliente);
        Cliente clienteCpf = clienteService.buscarPorCpf("37347795028");

        Endereco savedEndereco = new Endereco(1L, "Sanazar Mardiros, 508", "Osasco",
                "SP", "06210-000", clienteCpf);

        when(enderecoService.salvarEndereco(any(Endereco.class))).thenReturn(savedEndereco);

        savedEndereco = enderecoService.salvarEndereco(savedEndereco);

        //Valida se endereco foi salvo
        assertNotNull(savedEndereco);

        when(enderecoService.buscarPorId(anyLong())).thenReturn(savedEndereco);

        savedEndereco = enderecoService.buscarPorId(1L);

        //Valida a busca por id
        assertNotNull(savedEndereco);
        assertEquals("06210-000", savedEndereco.getCep());
        assertEquals("Osasco", savedEndereco.getCidade());
        verify(enderecoService, times(1)).buscarPorId(1L);

    }

}
