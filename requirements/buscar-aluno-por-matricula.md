# Buscar aluno por matrícula

## Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/students?enrollment={MATRICULA}**
2. Retorna **200** com os dados do aluno.

## Exceções

1. Retorna **404** se o aluno não for encontrado.