DESAFIO 08 — Sistema de Matrícula em Cursos

Contexto:

Criar um sistema simples de matricula de alunos em cursos.

Estrutura do desafio:

1 - Classe aluno

    Deve conter : Nome, construtor e getter.

2 - Classe curso

    Deve conter : Nome do curso, carga horária toal, construtor e getters.

3 - Classe matricula

    Deve conter : Lista de curso, método para adicionar curso, método totalCargaHoraria, método classificarMatricula.

    Regra : Se carga horária > 100, " Matrícula completa ", se não, " Matrícula comum ".

4 - Classe MatriculaService

    Responsável por : Cadastrar aluno ( retorna Aluno  ), Cadastra cursos e adiciona a matricula, exibir resumo final.

    Regra : NÂO pode ter atributos do tipo, Aluno, Curso ou Matrícula. Tudo deve ser recebido ou retornado.

5 Classe Main

    Deve : Criar Scanner, Criar MatriculaService e orquestrar o fluxo.