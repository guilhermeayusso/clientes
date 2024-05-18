package br.com.fiap.clientes.web.dto.mapper;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.web.dto.cliente.request.ClienteRequestDto;
import br.com.fiap.clientes.web.dto.cliente.response.ClienteConsultaResponseDto;
import br.com.fiap.clientes.web.dto.cliente.response.ClienteResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);


    @Mapping(source = "enderecos", target = "enderecos")
    ClienteRequestDto clienteToClienteRequestDto(Cliente cliente);


    @Mapping(source = "enderecos", target = "enderecos")
    Cliente clienteDTOToCliente(ClienteRequestDto clienteDTO);

    @Mapping(source = "enderecos", target = "enderecos")
    ClienteConsultaResponseDto clienteToClienteConsultaResponseDto(Cliente cliente);

    ClienteResponseDto clienteToClienteResponseDto(Cliente cliente);

}

