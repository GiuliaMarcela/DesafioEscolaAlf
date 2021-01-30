# Documentação - Desafio Escola Alf!

## Introdução

Este repositório foi criado com intuito de responder a um desafio, o qual era desenvolver uma API RESTful.
API foi desenvolvida com a linguagem de programação Java, utilizando o eclipse. Para fazer as requisições, utilizei o insomnia, onde foi possível interagir com a API.

## O Desafio

A escola Alf aplica provas de múltipla escolha para os alunos. A nota aluno na prova é determinada pela média ponderada das questões com os pesos de cada questão. Cada questão correta soma 1 ponto multiplicada pelo peso e cada questão errada 0. A nota final é a média aritmética das notas de todas as provas.

#### Requisitos obrigatórios:

Crie uma API HTTP que permita à escola:

    - Cadastrar o gabarito de cada prova;

    - Cadastrar as respostas de cada aluno para cada prova;

    - Verificar a nota final de cada aluno;

    - Listar os alunos aprovados;

#### Restrições

- A nota total da prova é sempre maior que 0 e menor que 10.

- A quantidade máxima de alunos é 100.

- O peso de cada questão é sempre um inteiro maior que 0.

- Os alunos aprovados tem média de notas maior do que 7.

- A entrada e saída de dados deverá ser em JSON.

## Como rodar a aplicação

Após efetuar o clone do repositório, dentro da pasta do repositório, existirão os seguintes arquivos:

>     - backend
>
> - escolaAlf-0.0.1-SNAPSHOT
> - README

Abra um terminal (de sua preferência), e execute o comando:

    java -jar escolaAlf-0.0.1-SNAPSHOT.jar

#### Documentação utilizando o Swagger

Com a aplicação já rodando, você poderá acessar a documentação da API feita pelo Swagger. Acessando a url:

    http://localhost:8080/swagger-ui.html

## Endpoints

### POST /register

> Este endpoint aceita apenas dados formatados em JSON.
> Efetua o cadastro de um gabarito.

    {
        "questions":  [
    	    {
    		"answer":  "string",
    		"optionNumber":  0,
    	    "weight":  0
           }
       ]
    }

### POST /student/register

> Este endpoint aceita apenas dados formatados em JSON.
> Efetua o cadastro das respostas do aluno

    {
        "id":  0,
        "name":  "string",
        "tests":  [  "string"  ]
    }

### GET /student/{id}

> Verificar a nota final de cada aluno;
> Esta requisição retorna a nota final do aluno que foi pesquisado na url.

### GET /approved

> Listar os alunos aprovados;
> Retorna uma lista com os alunos aprovados, mostrando sua nota final e seu status, aprovado = true.

    [
        {
    	    "id":  0,
    	    "name":  "string",
    	    "result":  0,
    	    "status":  true,
    	    "tests":  [  "string"  ]
    	 }
    ]

### GET /students

> Retorna uma lista de todos os alunos cadastrados.
> A quantidade máxima de alunos é 100

    [
        {
    	    "id":  0,
    	    "name":  "string",
    	    "result":  0,
    	    "status":  true,
    	    "tests":  [  "string"  ]
    	 }
    ]
