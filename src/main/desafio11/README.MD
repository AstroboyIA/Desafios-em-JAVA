DESAFIO 11 — Sistema de Controle de Treinos

Objetivo:

    Criar um sistema simples para registrar treinos realizados por um aluno e gerar um resumo final com base no volume total de treino.

Classes obrigatorias:

    Aluno:

        nome (String)

    Treino

        descricao (String)
        duracaoMinutos (int)

    RegistroTreino

        lista de Treino

        método para adicionar treino

        método para calcular tempo total de treino

        método que retorna:

            true → se tempo total ≥ 300 minutos
            false → caso contrário

    TreinoService

        Métodos esperados no Service:

            método de boas-vindas

            método para cadastrar aluno

            método para registrar treinos

            método para exibir resumo final