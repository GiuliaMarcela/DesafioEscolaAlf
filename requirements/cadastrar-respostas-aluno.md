# Cadastrar respostas do aluno

## Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/exams**
2. Valida dados obrigatórios
3. Cria as respostas com os dados fornecidos
4. Retorna **201** com o exame e o seu identificador

## Exceções

1. Retorna **404** se o aluno não for encontrado
