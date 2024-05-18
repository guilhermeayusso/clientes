package br.com.fiap.clientes.web.dto.cliente.response;

import br.com.fiap.clientes.web.dto.endereco.response.EnderecoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
}
