DESAFIO 06 - CONTROLE DE COMPRAS SIMPLES

Objetivo pedagógico

Consolidar:

    1 - Fluxo de dados entre classes;
    2 - Uso do construtor e getters;
    3 - Uso de lista com lógica simples;
    4 - Aplicação de regras de varíaveis

Contexto:

Um cliente faz compras simples em uma loja.

Ele pode adicionar produtos e, ao final, o sistema deve:

    1 - Calcular o total;
    2 - Aplicar regra simples;
    3 - Exixbir resumo.

Regras:

Classes obrigatórias:

    1 - Produto

    Deve conter:

        Nome ( String );
        Preço ( Double ).

    2 - Carrinho

    Deve conter:

        1 - Lista de produtos;
        2 - Método adicionarProduto;
        3 - Método calcularTotal.

    3 - Cliente

        1 - Nome ( String );
        2 - Método finalizarCompra( Carrinho carrinho ).

    4 - CompraService

    Responsável apenas por:

        1 - Ler dados do usuário;
        2 - Criar objetos;
        3 - Chamar métodos.

    5 - Main

        Apenas inicia o fluxo.

Restrições:

    Sem herança, sem interface, sem instaceof, sem desconto, sem VIP, sem exceções, sem multiplas regras.

Regra de négocio única:

    Se o total da compra for maior que 100, exibir a mensagem:

        "Compra de valor alto."

    Caso contrario:

        "Compra comum."