package br.com.fiap.clientes.web.dto.cliente.request;


import br.com.fiap.clientes.web.dto.endereco.request.EnderecoRequestDTO;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ClienteRequestDto {


    @NotEmpty(message = "O nome não pode ser vazio.")
    @Size(min = 5, max = 100)
    private String nome;

    @Email(message = "E-mail inválido.")
    private String email;

    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "O telefone deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotEmpty(message = "O cliente deve ter pelo menos um endereço.")
    private List<EnderecoRequestDTO> enderecos = new ArrayList<>();

}
