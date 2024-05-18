package util;

import br.com.fiap.clientes.repository.EnderecoRepository;
import br.com.fiap.clientes.web.dto.cliente.request.ClienteRequestDto;
import br.com.fiap.clientes.web.dto.endereco.request.EnderecoRequestDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestsUtils {

    public static ClienteRequestDto gerarMassaValidaParaClienteRequestDto() {
        ClienteRequestDto clienteRequestDto = new ClienteRequestDto();
        EnderecoRequestDTO enderecoRequestDto = new EnderecoRequestDTO();

        enderecoRequestDto.setCep("06213-000");
        enderecoRequestDto.setCidade("Osasco");
        enderecoRequestDto.setEstado("SP");
        enderecoRequestDto.setRua("Sanazar Mardiros, 508");

        List listaEndereco = new ArrayList();
        listaEndereco.add(enderecoRequestDto);

        clienteRequestDto.setCpf("94655744065");
        clienteRequestDto.setNome("Jose Pereira");
        clienteRequestDto.setEmail("jose.pereira@gmail.com");
        clienteRequestDto.setTelefone("(11) 97448-6556");
        clienteRequestDto.setEnderecos(listaEndereco);

        return clienteRequestDto;
    }
}
