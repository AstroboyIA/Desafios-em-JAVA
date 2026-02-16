🧩 DESAFIO 14 — Planejamento Inteligente de Projeto

🎯 Objetivo

Evoluir o sistema do Desafio 13 para responder uma pergunta real de gestão:

"Com o ritmo atual, o projeto termina no prazo?"

Você vai reforçar:

Encapsulamento com regras de validação

Model rico em comportamento (sem lógica no service)

Enum com classificação de risco

Cálculo de previsão com base em dados atuais


---

🏗️ Regras do domínio (leia com atenção)

📌 Projeto

Um Projeto agora também deve ter:

prazoHoras (int): quantidade total de horas planejadas para concluir o projeto

lista de tarefas (continua)

status calculado automaticamente (continua)

progresso calculado automaticamente (continua)


📍 Importante:
O prazo não substitui as tarefas.
Ele representa a meta de planejamento do projeto.


---

📌 Tarefa

A Tarefa continua com:

descricao

horasEstimadas

horasConcluidas


📍 Nova regra de validação:

a tarefa não pode ter horas negativas

horasConcluidas não pode ser maior que horasEstimadas


Se os dados forem inválidos, o model deve impedir o cadastro (ex.: IllegalArgumentException).


---

🧠 Regras de negócio (essenciais)

🔹 Regra 1 — Previsão de horas finais

O Projeto deve responder:

int calcularPrevisaoHorasFinais()

Lógica:

Some todas as horasEstimadas das tarefas.

Se não houver tarefas, previsão = 0.

A previsão representa o esforço total esperado para concluir o escopo atual.


---

🔹 Regra 2 — Situação de prazo

Crie um enum:

SituacaoPrazo

Com valores:

ADIANTADO

NO_PRAZO

ATRASADO

Crie no Projeto:

SituacaoPrazo calcularSituacaoPrazo()

Lógica:

Se previsaoHorasFinais < prazoHoras → ADIANTADO

Se previsaoHorasFinais == prazoHoras → NO_PRAZO

Se previsaoHorasFinais > prazoHoras → ATRASADO


📍 Essa decisão é do Projeto, não do Service.


---

🔹 Regra 3 — Percentual de conclusão real

O Projeto deve responder:

int calcularPercentualConclusaoReal()

Lógica:

baseado em horasConcluidas / prazoHoras

retorno inteiro de 0 a 100

se passar de 100, limitar em 100

se prazoHoras for 0, retornar 0


---

🧱 Estrutura esperada (não copie, interprete)

model/
 ├── Projeto
 ├── Tarefa
 ├── StatusProjeto (enum)
 ├── TamanhoProjeto (enum)
 ├── SituacaoPrazo (enum)  <- novo
service/
 └── ProjetoService
Main


---

🚦 O que você NÃO deve fazer

❌ Colocar if/else de regra de prazo no Service
❌ Permitir tarefa com horas inválidas sem erro
❌ Deixar Main calcular regra de negócio
❌ Duplicar lógica entre Projeto e Service


---

🧪 Critério de sucesso

Seu sistema deve conseguir imprimir algo como:

Projeto: Plataforma E-commerce
Status: Em andamento
Progresso: 45%
Previsão de esforço: 120h
Prazo planejado: 100h
Situação de prazo: Atrasado
Conclusão real (prazo): 54%


---

🧠 Dica final (não é solução)

> “Projeto saudável não é só o que conclui tarefa,
é o que conclui tarefa dentro de plano.”