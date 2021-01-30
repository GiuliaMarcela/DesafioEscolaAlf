# Documentação - Desafio Escola Alf!

A **escola Alf** aplica provas de múltipla escolha para os alunos. A nota do  
aluno na prova é determinada pela média ponderada das questões com os  
pesos de cada questão. Cada questão correta soma 1 ponto multiplicada pelo  
peso e cada questão errada 0. A **nota final** é a média aritmética das notas de todas as provas.

## Endpoints

> A entrada e saída de dados deverá ser em JSON

### POST /register

> Este endpoint aceita apenas dados formatados em JSON.
> Efetua o cadastro de um gabarito.

    {  "id":  0,  "questions":  [  {  "answer":  "string",  "id":  0,  "optionNumber":  0,  "weight":  0  }  ]  }

### POST /student/register

> Este endpoint aceita apenas dados formatados em JSON.

    {  "id":  0,  "name":  "string",   "tests":  [  "string"  ]  }

### GET /student/{id}

> Verificar a nota final de cada aluno;

### GET /approved

> Listar os alunos aprovados;

    [  {  "id":  0,  "name":  "string",  "result":  0,  "status":  true,  "tests":  [  "string"  ]  }  ]

### GET /students

> Retorna uma lista de alunos.
> A quantidade máxima de alunos é 100
