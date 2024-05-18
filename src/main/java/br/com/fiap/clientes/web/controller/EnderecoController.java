package br.com.fiap.clientes.web.controller;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import br.com.fiap.clientes.service.ClienteService;
import br.com.fiap.clientes.service.EnderecoService;
import br.com.fiap.clientes.web.dto.endereco.request.EnderecoRequestDTO;
import br.com.fiap.clientes.web.dto.endereco.response.EnderecoResponseDto;
import br.com.fiap.clientes.web.dto.mapper.EnderecoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/enderecos")
public class EnderecoController {

    private final EnderecoService service;
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> CriarNovoEnderecoParaCliente(@RequestBody @Valid EnderecoRequestDTO requestDTO)  {
            Endereco endereco = EnderecoMapper.INSTANCE.toEntity(requestDTO);
            Cliente cliente = clienteService.buscarPorCpf(requestDTO.getCpf_cliente());
            endereco.setCliente(cliente);
        return ResponseEntity.status(201).body(EnderecoMapper.INSTANCE.toDto(service.salvarEndereco(endereco)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> AtualizarEnderecoParaCliente(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO requestDTO) {
        Endereco endereco = EnderecoMapper.INSTANCE.toEntity(requestDTO);
        enderecoService.atualizarEndereco(endereco,id);
        return ResponseEntity.noContent().build();
    }
}
