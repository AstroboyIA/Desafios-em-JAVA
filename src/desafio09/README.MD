DESAFIO 09 — Sistema de Boletim Escolar

Objetivo: Consolidar listas, regras simples e delegação correta de responsabilidades.

Estrutura obrigatoria:

1 - Classe Aluno

    Deve conter : Nome, construtor e getter.

2 - Classe Nota

    Deve conter : Valor, construtor e getter.

3 - Classe Boletim

    Deve conter : Lista de notas, métodos : adicionarNota (Nota nota), calcularMedia, isAprovado.

    Regra: se media >= 7.0 aprovado.

4 - Classe BoletimService

    Responsável por : Interação com usuário (Scanner), cadastro de alunos, cadastro de notas, exibição do resultado final.

5 - Calsse Main

    Orquetra o fluxo: Cria Scanner, chama o service e encerra o programa.