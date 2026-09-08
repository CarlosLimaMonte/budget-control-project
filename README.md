# Budget Control

Sistema de gestão de financeiro desenvolvido para gerenciamento de receitas e despesas, com autenticação de usuário e dashboard para acompanhamento das movimentações financeiras.

Projeto foi desenvolvido com **Java + Spring Boot** no backend e **React** no frontend, utilizando **JWT** para autenticação e **MySql** para persistência de dados.

# Sobre o projeto


O Budget Control permite que cada usuário tenha suas próprias receitas e despesas, garantindo que as movimentações financeiras sejam associadas ao usuário autenticado.

A aplicação possui:

- Cadastro de usuários
- Login com autenticação via JWT
- Criação de despesas
- Listagem de despesas
- Exclusão de despesas
- Criação de receitas
- Listagem de receitas
- Exclusão de receitas
- Resumo financeiro
- Consulta de movimentações por período
- Separação de despesas por categoria
- Controle de acesso aos dados do usuário autenticado

O objetivo do projeto foi entender como o **backend** e **frontend** interagem entre si e colocar em prática tudo que já vinha sendo visto por mim.

---

# IA dentro do desenvolvimento do projeto

Durante o desenvolvimento do projeto nenhuma linha de código foi feita diretamente por IA, todo o desenvolvimento foi feita por mim utilizando a IA somente como auxílio para entender os conceitos aplicados no projeto.

# Próximos passos

- Melhorar a interface do dashboard
- Adicionar gráficos financeiros
- Melhorar filtros e consultas por período
- Adicionar validações mais completas
- Criar testes automatizados
- Documentar a API
- Publicar aplicação em produção
- Melhorar responsividade do frontend

# Como executar o projeto
### Pré-requisitos

Antes de executar o projeto, tenha instalado:

Java 21
Maven
Node.js
MySQL
Git
Backend

Clone o repositório:

git clone https://github.com/CarlosLimaMonte/budget-control-project.git

Entre na pasta do backend:

cd BudgetControl

Configure as variáveis de ambiente necessárias:

PASSWORD_BD=sua_senha
JWT_SECRET=sua_chave_secreta

Depois execute:

mvn spring-boot:run

O backend será iniciado localmente.

Frontend

Entre na pasta do frontend:

cd BudgetControlFrontEnd/budget-control-front

Instale as dependências:

npm install

Execute o projeto:

npm run dev

# Autor
## Carlos Eduardo

Projeto desenvolvido como parte do meu aprendizado e evolução no desenvolvimento de aplicações Java/Spring Boot e React.

**Aplicação:** Em breve  
**API:** Em breve
