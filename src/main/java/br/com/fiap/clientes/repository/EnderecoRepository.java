package br.com.fiap.clientes.repository;

import br.com.fiap.clientes.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}