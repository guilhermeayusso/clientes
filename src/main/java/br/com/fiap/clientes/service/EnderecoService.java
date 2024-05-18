package br.com.fiap.clientes.service;

import br.com.fiap.clientes.entities.Cliente;
import br.com.fiap.clientes.entities.Endereco;
import br.com.fiap.clientes.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public void salvarEnderecos(List<Endereco> enderecoList, Cliente cliente) {

        if (enderecoList != null && !enderecoList.isEmpty()) {
            for (Endereco endereco : enderecoList) {
                endereco.setCliente(cliente);
                enderecoRepository.save(endereco);
            }
        }
    }

    @Transactional
    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void atualizarEndereco(Endereco endereco, Long id) {
        Endereco enderecoParaAtualizacao = buscarPorId(id);

        enderecoParaAtualizacao.setCidade(endereco.getCidade());
        enderecoParaAtualizacao.setEstado(endereco.getEstado());
        enderecoParaAtualizacao.setCep(endereco.getCep());
        enderecoParaAtualizacao.setRua(endereco.getRua());
        enderecoRepository.save(enderecoParaAtualizacao);

    }


    @Transactional(readOnly = true)
    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Endereço id=%s não encontrado", id)));
    }

}
