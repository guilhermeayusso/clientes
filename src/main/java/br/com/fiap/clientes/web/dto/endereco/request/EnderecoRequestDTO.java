package br.com.fiap.clientes.web.dto.endereco.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    @NotEmpty(message = "A rua não pode ser vazia.")
    @Length(max = 100, message = "O nome da rua deve ter no máximo 100 caracteres.")
    private String rua;

    @NotEmpty(message = "A cidade não pode ser vazia.")
    private String cidade;

    @NotEmpty(message = "O estado não pode ser vazio.")
    private String estado;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o padrão 00000-000.")
    private String cep;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf_cliente;
}
