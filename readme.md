<div id="top"></div>
<h1 align="center">Challenge School Alf</h1>
<p align="center">
  <img alt="GitHub Actions status" src="https://github.com/GiuliaMarcela/challenge-school/actions/workflows/ci.yml/badge.svg"/>

  <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/GiuliaMarcela/challenge-school.svg" />

  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/GiuliaMarcela/challenge-school.svg" />

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/GiuliaMarcela/challenge-school.svg" />

  <a href="https://github.com/GiuliaMarcela/challenge-school/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/GiuliaMarcela/challenge-school.svg" />
  </a>

  <a href="https://github.com/GiuliaMarcela/challenge-school/issues">
    <img alt="Repository issues" src="https://img.shields.io/github/issues/GiuliaMarcela/challenge-school.svg" />
  </a>
</p>

Em janeiro de 2021, fiz parte de um processo seletivo quando eu tinha 3 meses de conhecimento em programação. Foi um
grande desafio, tive inúmeros impactos e dificuldades para desenvolver o projeto, e por muito tempo, achei que não
conseguiria fazer a parte mais básica. Continuei a passar nas etapas do processo, entreguei o projeto, mas não recebi
resposta
nenhuma sobre a vaga (depois descobri que acontece muito).

Entretanto, a minha primeira entrega não me agradou muito (aliás, nem sei se funcionava). Portanto, desafiei-me quase 2
anos depois a refazer o projeto com o meu conhecimento atual.

Pode encontrar o código da
minha [primeira versão](https://github.com/GiuliaMarcela/challenge-school/tree/first-version-api) aqui.

# Conteúdo

1. [Sobre](#sobre)
2. [Requisitos obrigatórios](#requisitos-obrigatórios)
   1. [Restrições](#restrições)
3. [Tecnologias e ferramentas utilizadas](#tecnologias-e-ferramentas-utilizadas)
4. [Como rodar](#como-rodar)
   1. [Localmente](#localmente)
   2. [Utilizando o docker](#no-docker)
5. [Casos de Uso](#casos-de-uso)
   1. [Cadastrar aluno](#cadastrar-aluno)
   2. [Cadastrar respostas do aluno](#cadastrar-respostas-do-aluno)
   3. [Verificar o exame cadastrado](#verificar-o-exame-cadastrado)
   4. [Cadastrar gabarito da prova](#cadastrar-gabarito-da-prova)
   5. [Verificar a nota final de cada aluno](#verificar-a-nota-final-de-cada-aluno)
   6. [Buscar aluno pela matrícula](#buscar-aluno-pela-matrícula)
   7. [Listar alunos aprovados](#listar-alunos-aprovados)
6. [Autores](#autores)

# Sobre

A escola Alf aplica provas de múltipla escolha para os alunos. A nota aluno na prova é determinada pela média ponderada
das questões com os pesos de cada questão. Cada questão correta soma 1 ponto multiplicada pelo peso e cada questão
errada 0. A nota final é a média aritmética das notas de todas as provas.

### Requisitos obrigatórios

Criar uma API restful, utilizando a linguagem java ou python.

- [x] Cadastrar o gabarito de cada prova
- [x] Cadastrar as respostas de cada aluno para cada prova
- [x] Verificar a nota final de cada aluno
- [x] Listar alunos aprovados

#### Restrições

- [x] A nota da prova é sempre maior que 0 e menor que 10
- [x] A quantidade máxima de alunos é 100
- [x] O peso de cada questão é sempre um inteiro maior que 0
- [x] Os alunos aprovados tem média de notas maior do que 7
- [x] A entrada e saída de dados deverá ser em JSON

# Tecnologias e ferramentas utilizadas

- Java
- Spring Framework
- Docker
- GitHub Actions
- Sonar Lint
- Gradle
- Mockito
- Mapstruct
- Lombok
- H2 Database
- Postgres

# Como rodar

### Localmente

1. Clone o repositório
2. No terminal (da sua preferência), vá até à pasta do projeto.
    ```bash
      cd challenge-school/
    ```
3. Abra o arquivo application.yaml e altere a **linha 6**, para **test**.
4. No seu terminal, execute o comando:
    ```bash
    ./gradlew clean build
    ```
5. Após ele terminar de executar o build da aplicação, execute o comando:
    ```bash
      java -jar build/libs/school-0.0.1-SNAPSHOT.jar
    ```

### No Docker

1. Clone o repositório
2. No terminal (da sua preferência), vá até à pasta do projeto.
    ```bash
      cd challenge-school/
    ```
3. Execute o comando:
   ```bash
      ./gradlew clean build
      docker compose up -d
   ```

[Back To Top](#top)

# Casos de uso

## Cadastrar aluno

### Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/students**
2. Valida dados obrigatórios
3. Cria um aluno com os dados fornecidos
4. Retorna **201** com o aluno criado com o identificador

### Exceções

1. Retorna **400** se já existe aluno com email cadastrado
2. Retorna **400** caso já tenha atingido o limite de 100 alunos cadastrados
3. Retorna **500** se der erro ao gerar uma matrícula

## Cadastrar respostas do aluno

### Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/exams**
2. Valida dados obrigatórios
3. Cria as respostas com os dados fornecidos
4. Retorna **201** com o exame e o seu identificador

### Exceções

1. Retorna **404** se o aluno não for encontrado

[Back To Top](#top)

## Verificar o exame cadastrado

### Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/exams?examId={IDENTIFICADOR_DO_EXAME}**
2. Retorna **200** com dados do exame encontrado.

### Exceções

1. Retorna **404** se o exame não for encontrado

## Cadastrar gabarito da prova

### Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/templates**
2. Valida dados obrigatórios
3. Cria o gabarito com os dados fornecidos
4. Retorna **201** com o gabarito e seu identificador

### Exceções

1. Retorna **400** se o peso de alguma questão for menor que 0 e maior que 10.
2. Retorna **400** se a soma final dos pesos for menor que 0 e maior que 10.
3. Retorna **400** se o gabarito e o exame informado não tiver a mesma quantidade de perguntas e respostas.
4. Retorna **404** se o exame requerido não for encontrado

## Verificar a nota final de cada aluno

### Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/students/grade?enrollment={MATRICULA}**
2. Retorna **200** com os dados do aluno.

### Exceções

1. Retorna **404** se o aluno não for encontrado.

## Buscar aluno pela matrícula

### Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/students?enrollment={MATRICULA}**
2. Retorna **200** com os dados do aluno.

### Exceções

1. Retorna **404** se o aluno não for encontrado.

## Listar alunos aprovados

### Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/students/approved**
2. Retorna **200** com uma página de alunos aprovados.
3. Retorna **200** com uma página vazia se ainda não houver alunos aprovados.

[Back To Top](#top)

# Autores

<table>
<td  align="center">
<a  href="https://github.com/GiuliaMarcela"  target="_blank">
<img  src="https://avatars.githubusercontent.com/u/65039041?v=4"  width="200"/>
<br />
<sub>Giulia Marcela</sub>
</a>
</td>
<td  align="left">
<sub>
Sou natural de Petrópolis, Rio de Janeiro.
Mas atualmente moro em Blumenau, Santa Catarina. No ano de 2020, fiz parte do Programa de Residência de Software, do Serratec. Uma imersão full stack, desde algoritmos ao desenvolvimento de aplicativos mobile.
</sub>
</td>
</table>
