# Cadastrar gabarito da prova

## Caso de sucesso

1. Recebe uma requisição do tipo **POST** na rota **/api/v1/templates**
2. Valida dados obrigatórios
3. Cria o gabarito com os dados fornecidos
4. Retorna **201** com o gabarito e seu identificador

## Exceções

1. Retorna **400** se o peso de alguma questão for menor que 0 e maior que 10.
2. Retorna **400** se a soma final dos pesos for menor que 0 e maior que 10.
3. Retorna **400** se o gabarito e o exame informado não tiver a mesma quantidade de perguntas e respostas.
4. Retorna **404** se o exame requerido não for encontrado