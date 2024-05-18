package br.com.fiap.clientes.web.controller;


import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.service.ClienteService;
import br.com.fiap.clientes.web.dto.cliente.request.ClienteRequestDto;
import br.com.fiap.clientes.web.dto.cliente.response.ClienteConsultaResponseDto;
import br.com.fiap.clientes.web.dto.cliente.response.ClienteResponseDto;
import br.com.fiap.clientes.web.dto.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> create (@RequestBody @Valid  ClienteRequestDto clienteRequestDto){
        Cliente cliente = ClienteMapper.INSTANCE.clienteDTOToCliente(clienteRequestDto);
        cliente = clienteService.salvar(cliente);

        ClienteResponseDto responseDto = ClienteMapper.INSTANCE.clienteToClienteResponseDto(cliente);

        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteConsultaResponseDto> buscarPorCpf(@PathVariable String cpf){
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        ClienteConsultaResponseDto responseDto = ClienteMapper.INSTANCE.clienteToClienteConsultaResponseDto(cliente);
        return ResponseEntity.ok(responseDto);
    }
}
