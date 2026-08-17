# Sistema de Matrícula

Sistema web acadêmico para gerenciamento de **professores**, **disciplinas**, **cursos**, **alunos**, **ofertas de disciplina** e **matrículas**, desenvolvido como projeto da disciplina de ITPDAPW (IFSP). A aplicação oferece cadastro, listagem, edição e exclusão para cada módulo, com uma tela principal de navegação e interface construída com Thymeleaf.

## Stack utilizada

| Camada          | Tecnologia                              |
|-----------------|------------------------------------------|
| Linguagem       | Java 21                                   |
| Framework       | Spring Boot 4.1.0                         |
| Persistência    | Hibernate ORM 7.4.1 (JPA) + queries nativas |
| Banco de dados  | MariaDB 11.8                              |
| Pool de conexão | HikariCP                                  |
| Template engine | Thymeleaf                                 |
| Front-end       | Bootstrap 5 + Bootstrap Icons             |
| Build           | Maven                                     |
| Hot reload      | Spring Boot DevTools                      |

## Estrutura do projeto

A estrutura reflete o padrão MVC do Spring, com uma camada adicional de `Repository` que acessa os dados via `EntityManager` (JPA e/ou queries nativas):

```
src/main/java/com/example/matriculadisciplina/
├── Controller/
│   ├── AlunoController.java
│   ├── CursoController.java
│   ├── DisciplinaController.java
│   ├── HomeController.java
│   ├── OfertaDisciplinaController.java
│   └── ProfessorController.java
│
├── Model/
│   ├── Aluno.java
│   ├── Curso.java
│   ├── Disciplina.java
│   ├── Matricula.java
│   ├── OfertaDisciplina.java
│   ├── Pessoa.java
│   ├── Professor.java
│   └── UF.java
│
├── Repository/
│   ├── AlunoRepository.java
│   ├── CursoRepository.java
│   ├── DisciplinaRepository.java
│   ├── OfertaDisciplinaRepository.java
│   └── ProfessorRepository.java
│
└── MatriculadisciplinaApplication.java
```

```
src/main/resources/
├── templates/
│   ├── index.html                 # Tela principal (HomeController)
│   ├── professor/
│   │   ├── listar.html
│   │   ├── cadastrar.html
│   │   └── editar.html
│   ├── aluno/ ...
│   ├── curso/ ...
│   ├── disciplina/ ...
│   └── formCadOfertaDisciplina.html
└── application.properties
```

> **Observação sobre `Matricula`**: o Model existe, mas ainda não há `MatriculaController` nem `MatriculaRepository` no projeto — a matrícula de um aluno é tratada, por ora, através do fluxo de `OfertaDisciplina` (que representa a oferta de uma disciplina em um período, vinculando professor/disciplina/turma). Se a matrícula do aluno nessa oferta precisar de CRUD próprio, esse módulo ainda precisa ser criado seguindo o mesmo padrão dos demais.

## Modelo de dados

O sistema usa herança de tabelas (`JOINED`) para representar pessoas que assumem papéis distintos:

```
pessoa (id_pessoa PK, nome, idade, email, telefone, endereco, cidade, uf)
   └── professor (id_pessoa PK/FK, siape, area, formacao)
   └── aluno     (id_pessoa PK/FK, ...)

curso (id_curso PK, nome, ...)
disciplina (id_disciplina PK, nome, carga_horaria, id_curso FK)
oferta_disciplina (id_oferta PK, id_disciplina FK, id_professor FK, periodo, ...)
matricula (id_matricula PK, id_aluno FK, id_oferta FK, ...)
```

`Professor` e `Aluno` estendem `Pessoa`; a tabela filha guarda apenas os campos específicos daquele papel, e a chave primária é compartilhada com `pessoa` via `id_pessoa`. `UF` é um enum com as siglas dos estados brasileiros, usado no cadastro de endereço de `Pessoa`.

## Pré-requisitos

- Java 21 (JDK)
- Maven 3.9+
- MariaDB 10.6+ (ou MySQL compatível) rodando localmente

## Configuração do banco de dados

1. Acesse o MariaDB como root:
   ```bash
   mysql -u root -p
   ```

2. Crie a database e o usuário da aplicação:
   ```sql
   CREATE DATABASE SistemaMatricula;

   CREATE USER 'superaluno'@'localhost' IDENTIFIED BY 'sua_senha_aqui';
   GRANT ALL PRIVILEGES ON SistemaMatricula.* TO 'superaluno'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. Configure `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mariadb://localhost:3306/SistemaMatricula
   spring.datasource.username=superaluno
   spring.datasource.password=sua_senha_aqui

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   > `ddl-auto=update` faz o Hibernate criar/ajustar as tabelas automaticamente a partir das entidades anotadas com `@Entity`. Em produção, prefira `validate` ou migrações versionadas (Flyway/Liquibase).

## Executando o projeto

Via Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Ou compilando o `.jar`:
```bash
./mvnw clean package
java -jar target/matriculadisciplina-0.0.1-SNAPSHOT.jar
```

A aplicação sobe em `http://localhost:8080`, com a tela principal servida pelo `HomeController`.

## Rotas por módulo

| Módulo             | Controller                     | Listar          | Cadastrar                  | Editar                      | Excluir                       |
|---------------------|---------------------------------|------------------|------------------------------|--------------------------------|----------------------------------|
| Professor           | `ProfessorController`          | `/professor`     | `/professor/cadastrar`      | `/professor/editar/{id}`      | `/professor/excluir/{id}`       |
| Aluno               | `AlunoController`               | `/aluno`         | `/aluno/cadastrar`          | `/aluno/editar/{id}`          | `/aluno/excluir/{id}`           |
| Curso               | `CursoController`               | `/curso`         | `/curso/cadastrar`          | `/curso/editar/{id}`          | `/curso/excluir/{id}`           |
| Disciplina          | `DisciplinaController`          | `/disciplina`    | `/disciplina/cadastrar`     | `/disciplina/editar/{id}`     | `/disciplina/excluir/{id}`      |
| Oferta de Disciplina| `OfertaDisciplinaController`   | `/ofertadisciplina` | `/ofertadisciplina/cadastrar` | `/ofertadisciplina/editar/{id}` | `/ofertadisciplina/excluir/{id}` |
| Matrícula           | *(ainda não implementado)*     | —                | —                             | —                                | —                                  |

> As rotas seguem o padrão observado no `ProfessorController`; confirme os `@RequestMapping` reais de `AlunoController`, `CursoController`, `DisciplinaController` e `OfertaDisciplinaController` caso algum deles use um caminho diferente.

## Repositórios

Todos os repositórios (`AlunoRepository`, `CursoRepository`, `DisciplinaRepository`, `OfertaDisciplinaRepository`, `ProfessorRepository`) seguem o padrão de acesso a dados via `EntityManager`, injetado com `@PersistenceContext`, expondo métodos `insert`, `update`, `delete`, `findById` e `findAll`. `ProfessorRepository` (e futuramente `AlunoRepository`, por herdar de `Pessoa`) precisa coordenar duas tabelas (`pessoa` + a tabela específica) em cada operação, seja via JPA (`persist`/`merge`/`find`) ou via queries nativas manuais com `LAST_INSERT_ID()` para obter o id gerado em `pessoa` antes de inserir na tabela filha.

## Solução de problemas comuns

**`Access denied for user 'superaluno'@'localhost'`**
Senha incorreta ou usuário sem privilégios na database. Verifique com:
```sql
SHOW GRANTS FOR 'superaluno'@'localhost';
```

**`No static resource X for request 'X'` (404 ao abrir um formulário)**
Falta o método `@GetMapping` correspondente no Controller — só existe o `@PostMapping` para salvar, mas não o `@GetMapping` para exibir a página.

**`Neither BindingResult nor plain target object for bean name 'X' available as request attribute`**
O template usa `th:object="${x}"` ou `th:field="*{campo}"`, mas o Controller não adicionou esse atributo ao `Model` antes de renderizar a view (ou usou um nome diferente).

**`NotReadablePropertyException: Invalid property 'campo' of bean class [...]`**
O campo referenciado em `th:field="*{campo}"` não existe (nem é herdado) na classe do objeto vinculado ao formulário. Confira o nome exato do getter/setter no Model.

**`Cannot add or update a child row: a foreign key constraint fails`**
Existem registros na tabela filha apontando para uma chave que não existe na tabela pai (dados órfãos). Localize com um `LEFT JOIN` e corrija ou remova os registros antes de deixar o Hibernate recriar a constraint.

**`class org.hibernate.mapping.JoinedSubclass cannot be cast to class org.hibernate.mapping.RootClass`**
Erro de mapeamento de herança JPA — normalmente causado por uma subclasse (`Professor`) declarando seu próprio `@Id`, quando na estratégia `JOINED` apenas a superclasse (`Pessoa`) deve ter `@Id`.

**`Unknown column 'id' in 'INSERT INTO'` ao usar query nativa em tabela filha**
A tabela da subclasse (ex: `professor`) usa `id_pessoa` como chave (compartilhada com `pessoa` via `@PrimaryKeyJoinColumn`), não uma coluna `id` própria. Ajuste o SQL manual para referenciar `id_pessoa`.

## Próximos passos sugeridos

- Implementar `MatriculaController` e `MatriculaRepository`, vinculando `Aluno` a uma `OfertaDisciplina`.
- Padronizar se `Aluno` também vai estender `Pessoa` (mesma abordagem já usada em `Professor`).
- Validar formulários (campos obrigatórios, formato de CPF/e-mail) antes de persistir.
- Adicionar testes automatizados para os repositórios e controllers.

## Licença

Projeto acadêmico desenvolvido para fins educacionais no IFSP — disciplina de ITPDAPW.
