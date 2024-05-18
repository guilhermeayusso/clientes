package br.com.fiap.clientes.service;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import br.com.fiap.clientes.exception.CpfUniqueViolationException;
import br.com.fiap.clientes.repository.ClienteRepository;
import br.com.fiap.clientes.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public Cliente salvar (Cliente cliente){

        List enderecosSave = new ArrayList<>();

        if(cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()){
            for(Endereco endereco : cliente.getEnderecos()){
                enderecosSave.add(endereco);
            }
        }

        if(cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()){
            cliente.getEnderecos().clear();
        }

        try {
            cliente = clienteRepository.saveAndFlush(cliente);
        }catch (DataIntegrityViolationException ex){
            throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, já existe no sistema", cliente.getCpf()));
        }

        enderecoService.salvarEnderecos(enderecosSave,cliente);

        return cliente;
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf).orElseThrow(
                ()-> new EntityNotFoundException(String.format("Cliente com CPF '%s' não encontrado", cpf))
        );
    }


}
