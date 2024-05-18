# Clientes API

## Objetivo
Este microsserviço é responsável pelo cadastro de clientes e endereços desses clientes. A API fornece funcionalidades para criar, atualizar, visualizar e deletar registros de clientes e seus respectivos endereços.

## Tecnologias Utilizadas
- **Linguagem de Programação**: Java 17
- **Framework**: Spring Boot 3.2.0
- **Banco de Dados**: MySQL 5.7
- **Documentação da API**: Swagger UI

## Configuração de Ambiente

### Local
Para rodar a aplicação localmente, siga os passos abaixo:

1. Certifique-se de que o Docker está instalado em sua máquina.
2. Navegue até o diretório do projeto e execute o seguinte comando para configurar e iniciar o banco de dados MySQL:

   ```bash
   docker-compose -f ./docker-compose-local.yml up -d
   ```
3. Certifique-se de que o arquivo `application.properties` esteja configurado com:
      ```bash
   spring.profiles.active=local
   ```
4. A aplicação pode ser executada diretamente através de sua IDE favorita, desde que o Java 17 esteja configurado corretamente.

### Full Docker
Para uma configuração completa utilizando Docker, incluindo a aplicação e o banco de dados, execute o seguinte comando:
```bash
docker-compose -f ./docker-compose-local.yml up -d
```

## Acesso à API
Após a aplicação estar em execução, você pode acessar a documentação da API e testar os endpoints através do Swagger UI no seguinte endereço:
[http://localhost:8080/docs-clientes.html](http://localhost:8080/docs-clientes.html)

## Observações
- É necessário ter o Docker e o Java 17 instalados em sua máquina para executar a aplicação.
- Assegure-se de verificar os logs do Docker em caso de problemas ao iniciar os serviços.
