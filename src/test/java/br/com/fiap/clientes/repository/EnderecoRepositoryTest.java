package br.com.fiap.clientes.repository;


import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EnderecoRepositoryTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Test
    public void testCreateEndereco() {

        Cliente novoCliente = new Cliente(null, "João Silva", "joao@example.com", "999999999", "37347795028", null);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(novoCliente);
        Cliente savedCliente = clienteRepository.save(novoCliente);

        when(clienteRepository.findByCpf(any(String.class))).thenReturn(Optional.of(savedCliente));
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf("37347795028");

        Endereco savedEndereco = new Endereco(null, "Sanazar Mardiros, 508", "Osasco",
                "SP","06210-000", clienteOptional.get());
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(savedEndereco);

        savedEndereco = enderecoRepository.save(savedEndereco);

        assertNotNull(savedEndereco);
        assertEquals("06210-000", savedEndereco.getCep());
        assertEquals("Osasco", savedEndereco.getCidade());

    }

    @Test
    public void testUpdateEndereco(){

        Cliente novoCliente = new Cliente(1L, "João Silva", "joao@example.com", "999999999", "37347795028", null);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(novoCliente);
        Cliente savedCliente = clienteRepository.save(novoCliente);

        when(clienteRepository.findByCpf(any(String.class))).thenReturn(Optional.of(savedCliente));
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf("37347795028");

        Endereco savedEndereco = new Endereco(1L, "Sanazar Mardiros, 508", "Osasco",
                "SP","06210-000", clienteOptional.get());
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(savedEndereco);

        savedEndereco = enderecoRepository.save(savedEndereco);

        assertNotNull(savedEndereco);
        assertEquals("06210-000", savedEndereco.getCep());
        assertEquals("Osasco", savedEndereco.getCidade());

        when(enderecoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(savedEndereco));

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(Long.valueOf(1L));

        savedEndereco = enderecoOptional.get();
        savedEndereco.setCep("06210-009");

        savedEndereco = enderecoRepository.save(savedEndereco);

        assertNotNull(savedEndereco);
        assertNotEquals("06210-000", savedEndereco.getCep());
        assertEquals("06210-009", savedEndereco.getCep());

    }
}
