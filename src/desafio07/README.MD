DESAFIO 07  - CONTROLE DE EMPRESTIMOS (BIBLIOTECA)

Desafio intencionalmente muito parecido com o anterior, se parecer " fácil demais", está no caminho certo.

Objetivo pedagógico

Fixar:

    1 - Quem calcula;

    2 - Quem coordena

    3 - Quem apenas inicia;

    4 - Como listas funcionam na prática;

    5 - Como regras simples usam variáveis.

Contexto:

Um sistema simples de biblioteca onde um usuário pode:

    1 - Cadastrar um leitor;

    2 - Adicionar livros a um empréstimo;

    3 - Visualizar o resumo final.

Classes obrigatórias:

    Livro:

        1 - Título;
        2 - Autor.

    Construtor obrigatório.
    Sem lógica.

    Emprestimo:

        1 - Lista de livros;
        2 - Método adicionarLivro(Livro livro);
        3 - Método calcularQuantidade().

    Nada de Scanner aqui.

    Leitor:

        1- Nome;
        2 - Método finalizarEmprestimo(Emprestimo emprestimo).

    Esse método não calcula, apenas delega.

    EmprestimoService:

    Responsável por:

        1 - Ler dados do usuário;
        2 - Criar objetos;
        3 - Chamar métodos.

    Sem cálculos aqui.

    Main:
        1 - Cria o fluxo;
        2 - Sem lógica.

Regra de negócio ÚNICA:

    Se a quantidade de livros emprestados for maior que 3, exibir:
    “Empréstimo grande”
    Caso contrário:
    “Empréstimo comum”
