## Passo a Passo para Rodar a API Localmente

### Pré-requisitos

- **Java 17**: Certifique-se de que o Java 17 está instalado. Você pode verificar a versão instalada com o comando:
  ```bash
  java -version
  ```
- **PostgreSQL**: Instale o PostgreSQL e tenha as credenciais de um banco de dados disponível (nome, usuário e senha).
- **Postman** (opcional): Para testar a API, o Postman ou outro cliente HTTP pode ser útil.

### Instruções

1. **Clone o Repositório**:
   - Clone o projeto com o seguinte comando:
     ```bash
     git clone https://github.com/SThalyta/Gerenciador-de-estoque---Spring-boot-3.git
     ```
   - Entre na pasta do projeto:
     ```bash
     cd Gerenciador-de-estoque---Spring-boot-3
     ```

2. **Configuração do Banco de Dados**:
   - No PostgreSQL, crie um banco de dados para o projeto:
     ```sql
     CREATE DATABASE nome_do_banco;
     ```
   - Atualize o arquivo `application.properties` na pasta `src/main/resources/` com as informações de conexão com o banco de dados PostgreSQL. Exemplo:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Baixar as Dependências**:
   - Certifique-se de que as dependências no `pom.xml` estão atualizadas. Você pode fazer isso executando o comando Maven:
     ```bash
     ./mvnw clean install
     ```
   - Ou, se estiver usando uma IDE (como IntelliJ ou Eclipse), basta abrir o projeto e permitir que a IDE faça o download automático das dependências do `pom.xml`.

4. **Rodar a Aplicação**:
   - Agora você pode iniciar a aplicação com o seguinte comando:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Ou rodando diretamente a classe principal (`@SpringBootApplication`) na sua IDE.

5. **Testando a API**:
   - Com a aplicação em execução, você pode usar o Postman (ou outro cliente HTTP) para enviar requisições para os endpoints disponíveis. Por exemplo, para testar a rota `GET /products`, envie uma requisição `GET` para:
     ```
     http://localhost:8080/products
     ```

### Observações

- Verifique se a aplicação está rodando na porta correta (padrão é `8080`). Caso queira mudar, ajuste o `server.port` no `application.properties`.
- Para automatizar a criação de tabelas, certifique-se de que a configuração `spring.jpa.hibernate.ddl-auto` esteja definida como `update`, para que o Hibernate crie as tabelas automaticamente com base nas entidades do projeto.
