Esse desafio foi pensado para ser um nível acima do desafio 04v2.

A ideia principal do desafio é :

1 - Reforçar POO
2 - Introdução ao conceito de regras de negócio simples
3 - Focar em melhorar a organização do código
4 - Manter a interação via Terminal
5 - Sem exceções customizadas
6 - Sem testes
7 - Sem Maven


DESAFIO 05 - SISTEMA DE GESTÃO DE PEDIDOS 

Contexto : 

Uma lanchonete deseja informatizar o processo de criação de pedidos.
O sistema será executado no terminal, sem interface gráfica, e deve permitir a criação de pedidos associados a clientes, com cálculo automático do valor final.

Este desafio tem como foco consolidação de Programação Orientada a Objetos, organização de código e aplicação de regras de negócio simples.

Objetivos de Aprendizado :

Ao concluir este desafio, você deverá demonstrar que é capaz de:

1 - Modelar corretamente classes e responsabilidades
2 - Utilizar herança de forma adequada
3 - Trabalhar com coleções (List)
4 - Separar lógica de negócio do método main
5 - Implementar regras de negócio simples de forma clara

Escopo do Sistema :

1 - Cliente -

    Atributos Obrigatórios do Cliente:

        Nome ( String )

    Comportamento do Cliente:

        1 - Obter nome;
        2 - Calcular valor final do pedido.

2 - Cliente VIP -

    O cliente VIP é um tipo especial de cliente.

        Regras do Cliente VIP:

        1 - Deve herdar Cliente;
        2 - Possui desconto fixo de 10% sobre o valor total do produto.

3 - Produto -

    Atributos obrigatórios do Produto:

        1 - Nome ( String );
        2 - Preço ( Double ).

    Regras do Produto :

        1 - O preço não pode ser negativo.

4 - Pedido -

    Atributos obrigatórios do Pedido:

        1 - Cliente associado;
        2 - Lista de Produtos ( List<Produtos> ).

    Comportamento obrigatório do Pedido:

        1 - Adicionar produto;
        2 - Remover produto;
        3 - Calcular valor total SEM DESCONTO;
        4 - Verificar se o pedido possui pelo menos um produto.

5 - Serviço do Pedido - 

    A lógica de negócio deve ficar no Main.

    Crie uma classe responsável por :

        1 -  Criar pedidos;
        2 - Calcular valor final do pedido considerando o tipo de cliente;
        3 - Exibir resumo do pedido.


- Estrutura OBRIGATÓRIA de Pacontes -

      desafio05/
    ├─ Main.java
    ├─ model/
    │  ├─ Cliente.java
    │  ├─ ClienteVip.java
    │  ├─ Produto.java
    │  └─ Pedido.java
    └─ service/
        └─ PedidoService.java

Observação:
    Nomes de pacotes e classes devem seguir exatamente o padrão acima.

- Regras de Negócio Obrigatórias -

    1 - Um pedido não pode ser finalizado sem produtos;
    2 - Cliente VIP recebe 10% de desconto;
    3 - Cliente comum não recebe desconto;
    4 - O cálculo do valor final não pode estar no Main;
    5 - O Main deve conter apenas fluxo e chamadas de métodos.