Projeto de Gerenciamento de Conta
=================================

Este projeto foi desenvolvido para avaliação técnica no teste de nivelamento da UBS.
    
Este projeto utiliza:
JDK 8;
Spring Boot;
Spring Data;
JPA;
Junit 5;
Mockito

## Como executar o projeto
- Banco:
    - Existem duas formas para se acessar banco.
        - A primeira e mais fácil é ir no application.properties `do aplicativo` e comentar o bloco `SQL SERVER` e descomentar o bloco `banco em memória`.
        - A segunda, consiste na necessidade de executar os scripts na pasta [sql](src/sql) em um banco sql server e ajustar o `application.properties` para apontar para esse banco.
        Para isso é necessário descomentar o bloco `SQL SERVER` e comentar o bloco `banco em memória`.
        
- Execução via maven:
    - No diretório do projeto execute o comando: mvn spring-boot:run

## Acessando as apis
Para iniciar o import, coloque os arquivos na pasta raiz/files (C:/files) e execute a requisição: 
`PUT localhost:8080/api/product/import
 Accept: */*
 Cache-Control: no-cache`
 
Após a importação dos dados, para a execução do cálculo, execute a requisição: 
`GET localhost:8080/api/product/calculateSell?product=EMMS&establishmentQuantity=2
 Accept: */*
 Cache-Control: no-cache`
