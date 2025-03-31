# 📌 CRUD de Pessoas e Endereços - Spring Boot

## 📖 Sobre o Projeto
Este projeto consiste em uma API RESTful desenvolvida com **Java 24** e **Spring Boot**, implementando um **CRUD** (Create, Read, Update, Delete) para as entidades **Pessoa** e **Endereço**. O relacionamento entre essas entidades é de **um-para-muitos**, onde uma pessoa pode ter um ou mais endereços.

O objetivo é demonstrar boas práticas de desenvolvimento com **arquitetura limpa**, validação de dados, tratamento de exceções e organização eficiente do código.

---

## 🚀 Tecnologias Utilizadas
- **Java 21** 
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Validation)
- **Banco de Dados H2** (em memória, para testes)
- **Maven** (gerenciamento de dependências)
- **Lombok** (redução de boilerplate)
- **Spring Validation** (validação de campos obrigatórios e formato de dados)

---

## 📌 Funcionalidades Implementadas
✅ **Listar todas as pessoas e seus respectivos endereços**  
✅ **Criar uma nova pessoa com um ou mais endereços**  
✅ **Atualizar os dados de uma pessoa e/ou seu(s) endereço(s)**  
✅ **Excluir uma pessoa e todos os seus endereços**  
✅ **Mostrar a idade da pessoa automaticamente**  
✅ **Validar CPF único na base de dados**  
✅ **Retornar todas as respostas em formato JSON**  
✅ **Tratamento de exceções para entradas inválidas**  
✅ **Definir qual endereço é o principal da pessoa**  
✅ **Paginar listagem de pessoas**

---

## 📌 Requisitos das Entidades
### **Pessoa**
| Campo | Tipo | Restrições |
|--------|------|-------------|
| ID | Long | Gerado automaticamente |
| Nome | String | Obrigatório |
| Data de Nascimento | LocalDate | Deve ser uma data no passado |
| CPF | String | Obrigatório, deve conter 11 dígitos e ser único |

### **Endereço**
| Campo | Tipo | Restrições |
|--------|------|-------------|
| ID | Long | Gerado automaticamente |
| Rua | String | - |
| Número | String | - |
| Bairro | String | - |
| Cidade | String | - |
| Estado | String | - |
| CEP | String | - |
| Principal | Boolean | Define se é o endereço principal |

---

## 🔄 Endpoints da API
### **PessoaController**
- `GET /pessoas` → Retorna a lista de todas as pessoas e seus endereços.
- `GET /pessoas/{id}` → Retorna uma pessoa pelo ID.
- `POST /pessoas` → Cria uma nova pessoa com um ou mais endereços.
- `PUT /pessoas/{id}` → Atualiza uma pessoa pelo ID.
- `DELETE /pessoas/{id}` → Exclui uma pessoa e todos os seus endereços.

### **EnderecoController**
- `GET /enderecos/pessoa/{pessoaId}` → Lista os endereços de uma pessoa.
- `POST /enderecos/pessoa/{pessoaId}` → Adiciona um novo endereço para uma pessoa.
- `PUT /enderecos/{enderecoId}` → Atualiza um endereço pelo ID.
- `DELETE /enderecos/{enderecoId}` → Exclui um endereço pelo ID.
- `PATCH /enderecos/{pessoaId}/definir-principal/{enderecoId}` → Define um endereço como principal.

---

## 🔥 Diferenciais Implementados
- **Paginação na listagem de pessoas** 🔄
- **Swagger para documentação da API** 📄
- **Tratamento global de exceções** 🚨
- **Uso de DTOs para requisições e respostas mais organizadas** 🗂️

---

## 🚀 Como Executar o Projeto
### **Pré-requisitos**
- Java 21 instalado
- Maven instalado

### **Passos para rodar o projeto**
```sh
# Clonar o repositório
git clone https://github.com/seu-repositorio.git

# Entrar no diretório do projeto
cd nome-do-projeto

# Construir e rodar a aplicação
mvn spring-boot:run
```
A API estará disponível em: `http://localhost:8080`

---

## 📌 Como Acessar o Banco H2
O projeto usa um banco de dados em memória (**H2**). Para acessar a interface web do H2:
- Abra um navegador e acesse: `http://localhost:8080/h2-console`
- Use as credenciais padrão:
    - **JDBC URL:** `jdbc:h2:mem:crud`
    - **Usuário:** `sa`
    - **Senha:** *(em branco)*

---

## 🛠️ Autor e Contato
Desenvolvido por **[Seu Nome]** 💻✨
- 🔗 LinkedIn: [seu-linkedin](https://www.linkedin.com/in/brunna-r-6516b7231/)
- 📧 Email: [seu-email@email.com](brunnadornelles407@gmail.com)

---

🚀 **Projeto desenvolvido seguindo as melhores práticas de código, arquitetura e design RESTful!**

