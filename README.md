# Teste_do_caso_do_Professor_Carlos

## Instruções para Executar o Sistema

1. **Pré-requisitos**
   - Java 17 ou superior
   - Maven 3.6 ou superior
   - Banco de dados (MySQL, PostgreSQL, etc.) configurado e acessível
     
**Executar o Projeto**

1. Clone o Repositório ->
  git clone https://github.com/Jhonatan-Gutemberg/Teste_do_caso_do_Professor_Carlos.git
  cd seu-repositorio

2. Configuração do Banco de Dados:
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 
   spring.datasource.url=jdbc:mysql://localhost:3306/system_db?useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=false
   
3. Instale as Dependências:
   mvn clean install
   
4. Execute o Projeto:
   mvn spring-boot:run

**Testar o Backend**
   - Acesse `http://localhost:8080` para verificar se o servidor está rodando.
   - Use ferramentas como Postman ou cURL para testar os endpoints da API.


6. Localize o front:
   cd frontend
   
7. Execute o React:
   npm start

## Lista de Premissas Assumidas

- O sistema assume que o banco de dados está configurado corretamente e acessível.
- As credenciais do banco de dados são fornecidas no arquivo de configuração do Spring Boot.
- O backend está implementado e funcionando conforme as especificações.
- O frontend foi configurado para se conectar ao backend na URL padrão (`http://localhost:8080`).

## Decisões de Projeto

1. **Modelagem do Banco de Dados**
   
   - Utiliza o padrão de entidade-relacionamento com tabelas separadas para `Student`, `Discipline`, e `StudentDiscipline`.
   - A relação entre `Student` e `Discipline` é gerenciada através da tabela `StudentDiscipline` para permitir uma associação muitos-para-muitos.
   - Padrão MVC: O projeto segue o padrão MVC para separar a lógica de apresentação, controle e acesso a dados.   
   - Model: Representa as entidades e a lógica de negócios.
   - View: Implementada no React, cuida da interface com o usuário.
   - Controller: Gerencia as requisições e interage com o modelo para retornar as respostas apropriadas.
   - Persistência de Dados: Utiliza o JPA para a persistência e manipulação de dados no banco de dados.
     
Cálculo e Exibição de Médias:

   - A média das notas de cada aluno e a média da turma em cada disciplina são calculadas e armazenadas.
   - O sistema também calcula a frequência geral de cada aluno e armazena esses dados no banco de dados.
Alertas e Relatórios:
   - O sistema permite identificar alunos com média acima da média da turma e aqueles com frequência abaixo de 75% para atenção especial.

**Endpoints da API**
   - Implementamos endpoints RESTful para CRUD de alunos e disciplinas, bem como endpoints para calcular e obter dados estatísticos relevantes.
   - Exemplo de endpoints incluem:
     - `GET /students` - Obtém a lista de todos os alunos.
     - `POST /students` - Cria um novo aluno.
     - `POST /students/{studentId}/add-disciplines` - Adiciona disciplina a um aluno.
     - `GET /students/average-grades` - Apresenta notas de alunos acima da média.
     - `GET /students/above-average` - Obtém alunos com média acima da média da turma.
     - `GET /students/below-frequency` - Obtém alunos com frequência abaixo de 75%.




