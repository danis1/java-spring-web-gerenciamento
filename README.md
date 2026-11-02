# API de Gerenciamento de Tarefas (Spring Boot)

API REST para gerenciar tarefas, desenvolvida na atividade **07 - Spring Web** (FATEC).

## Tecnologias
- Java 17
- Spring Boot (Spring Web, Spring Data JPA, Validation)
- PostgreSQL
- Maven

## Banco de dados
A tabela `tarefa` é criada automaticamente pelo Hibernate (`spring.jpa.hibernate.ddl-auto=update`) quando a aplicação sobe. O banco usado é o configurado em `application.properties`.

## Entidade Tarefa
| Campo | Tipo | Restrição |
|-------|------|-----------|
| id | Long | chave primária, gerada automaticamente |
| descricao | String | não nulo |
| concluida | Boolean | padrão `false` |
| prioridade | Integer | validado de 1 a 5 |

## Endpoints

Todos ficam sob a rota `/tarefas`.

### Criar tarefa
`POST /tarefas`
Recebe a tarefa em JSON e salva no banco. Retorna **201 Created** com a tarefa e o `id` gerado, ou **400 Bad Request** se a prioridade estiver fora de 1 a 5.

### Listar tarefas
`GET /tarefas`
Retorna a lista com todas as tarefas cadastradas (**200 OK**). Se não houver nenhuma, retorna uma lista vazia.

### Buscar tarefa por id
`GET /tarefas/{id}`
Retorna a tarefa com o id informado (**200 OK**) ou **404 Not Found** se ela não existir.

### Atualizar tarefa
`PUT /tarefas/{id}`
Atualiza descrição, status e prioridade da tarefa. Retorna **200 OK** com a tarefa atualizada, **400 Bad Request** se a prioridade for inválida ou **404 Not Found** se o id não existir.

### Deletar tarefa
`DELETE /tarefas/{id}`
Remove a tarefa. Retorna **204 No Content** ou **404 Not Found** se o id não existir.

## Como rodar
1. Criar o banco no PostgreSQL com o nome configurado em `application.properties`.
2. Ajustar usuário e senha em `src/main/resources/application.properties`.
3. Executar a classe principal da aplicação.
4. A API fica disponível em `http://localhost:8080/tarefas`.
