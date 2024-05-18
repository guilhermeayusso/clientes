package br.com.fiap.clientes.repository;


import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql(scripts = "/sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
public class EnderecoRepositoryIT {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void testCreateEndereco() {

        Optional<Cliente> cliente = clienteRepository.findByCpf("37347795028");
        assertTrue(cliente.isPresent());

        Endereco endereco = new Endereco();

        endereco.setCliente(cliente.get());
        endereco.setId(10L);
        endereco.setRua("Rua Sanazar Mardiros, 508");
        endereco.setCep("06210-00");
        endereco.setEstado("SP");
        endereco.setCidade("Osasco");

        Endereco savedEndereco = enderecoRepository.save(endereco);

        assertNotNull(savedEndereco);
        assertNotNull(savedEndereco.getId());
        assertEquals(endereco.getCep(), savedEndereco.getCep());
        assertEquals(endereco.getRua(), savedEndereco.getRua());

    }

    @Test
    public void testUpdateEndereco() {
        Optional<Endereco> enderecoRecuperado = enderecoRepository.findById(10L);
        assertTrue(enderecoRecuperado.isPresent());

        Endereco enderecoAtualizado = enderecoRecuperado.get();

        enderecoAtualizado.setCep("06210-05");

        Endereco savedEndereco = enderecoRepository.save(enderecoAtualizado);

        assertNotNull(savedEndereco);
        assertNotNull(savedEndereco.getId());
        assertEquals(enderecoAtualizado.getCep(), savedEndereco.getCep());
    }
}
