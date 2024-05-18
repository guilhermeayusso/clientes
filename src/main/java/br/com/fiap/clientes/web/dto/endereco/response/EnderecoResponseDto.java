package br.com.fiap.clientes.web.dto.endereco.response;

import lombok.*;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDto {

    Long id;
    String rua;
    String cidade;
    String estado;
    String cep;
}