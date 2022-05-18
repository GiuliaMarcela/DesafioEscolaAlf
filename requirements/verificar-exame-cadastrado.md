# Verificar o exame cadastrado

## Caso de sucesso

1. Recebe uma requisição do tipo **GET** na rota **/api/v1/exams?examId={IDENTIFICADOR_DO_EXAME}**
2. Retorna **200** com dados do exame encontrado.

## Exceções

1. Retorna **404** se o exame não for encontrado