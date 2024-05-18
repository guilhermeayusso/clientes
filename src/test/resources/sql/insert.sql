-- Inserção de dados
INSERT INTO clientes (id, nome, email, telefone, cpf) VALUES
                                                     (10,'João Silva', 'joao.silva@example.com', '11987654321', '37347795028'),
                                                     (20,'Maria Oliveira', 'maria.oliveira@example.com', '21987654321', '51255869003');

INSERT INTO enderecos (id,cliente_id, rua, cidade, estado, cep) VALUES
                                                                                     (10,10, 'Rua das Flores', 'São Paulo', 'SP', '01002030'),
                                                                                     (20,20, 'Avenida Brasil', 'Rio de Janeiro', 'RJ', '22081030');