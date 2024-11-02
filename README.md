# Gerenciador de Usuários

## Descrição
Um sistema simples para gerenciamento de usuários, permitindo adicionar, atualizar, excluir e autenticar usuários. Este projeto utiliza Java e JDBC para a interação com um banco de dados MySQL.

## Tecnologias Usadas
- **Java**: Linguagem de programação principal.
- **JDBC**: API para conectar e executar consultas com o banco de dados MySQL.
- **MySQL**: Sistema de gerenciamento de banco de dados usado para armazenar informações de usuários.

## Como Executar o Projeto

1. **Pré-requisitos**:
    - Java JDK (versão 8 ou superior).
    - MySQL instalado e configurado.

2. **Configuração do Banco de Dados**:
    - Crie um banco de dados MySQL chamado `user_management`:
      ```sql
      CREATE DATABASE user_management;
      ```

3. **Clone o Repositório**:
    - Execute o seguinte comando para clonar o repositório:
      ```bash
      git clone https://github.com/andrezktt/user-management-system.git
      ```

4. **Executar o Projeto**:
    - Navegue até o diretório do projeto e execute o arquivo `Main.java`:
      ```bash
      java Main
      ```

## Uso
Após executar o projeto, um menu será exibido com as seguintes opções:
1. Adicionar novo usuário
2. Atualizar informações do usuário
3. Alterar senha do usuário
4. Excluir usuário
5. Visualizar todos os usuários
6. Login
7. Logout
0. Sair

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues no repositório.

## Licença
Este projeto está licenciado sob a [MIT License](LICENSE).