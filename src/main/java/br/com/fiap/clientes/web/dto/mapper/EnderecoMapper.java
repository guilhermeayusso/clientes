package br.com.fiap.clientes.web.dto.mapper;

import br.com.fiap.clientes.entities.Endereco;
import br.com.fiap.clientes.web.dto.endereco.request.EnderecoRequestDTO;
import br.com.fiap.clientes.web.dto.endereco.response.EnderecoResponseDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);

    EnderecoResponseDto toDto(Endereco endereco);

}