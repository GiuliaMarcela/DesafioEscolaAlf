# Cadastrar aluno

## Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/students**
2. Valida dados obrigatórios
3. Cria um aluno com os dados fornecidos
4. Retorna **201** com o aluno criado com o identificador

## Exceções

1. Retorna **400** se já existe aluno com email cadastrado
2. Retorna **400** caso já tenha atingido o limite de 100 alunos cadastrados
3. Retorna **500** se der erro ao gerar uma matrícula