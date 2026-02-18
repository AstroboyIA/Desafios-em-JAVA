Perfeito. Então vamos subir o nível com intenção, sem pular degraus.
O Desafio 13 vai consolidar tudo que você aprendeu e introduzir responsabilidade real entre classes.


---

🧩 DESAFIO 13 — Sistema de Acompanhamento de Projetos

🎯 Objetivo

Evoluir o sistema do Desafio 12 para acompanhar status, progresso e conclusão de projetos, reforçando:

Encapsulamento

Enum com comportamento

Regras no model, não no service

Leitura de código orientada a domínio



---

🏗️ Regras do domínio (leia com atenção)

📌 Projeto

Um Projeto agora:

Tem um nome

Tem uma lista de tarefas

Tem um status

Pode estar:

NAO_INICIADO

EM_ANDAMENTO

CONCLUIDO



📍 Importante:
O status não é digitado pelo usuário, ele é calculado automaticamente.


---

📌 Tarefa

Uma Tarefa agora:

Tem descricao

Tem horasEstimadas

Tem horasConcluidas

Pode ser marcada como concluída


📍 Uma tarefa é considerada concluída quando:

horasConcluidas >= horasEstimadas


---

🧠 Regras de negócio (essenciais)

🔹 Regra 1 — Status do Projeto

O status do projeto deve ser calculado assim:

Situação das tarefas	Status do projeto

Nenhuma tarefa	NAO_INICIADO
Algumas concluídas	EM_ANDAMENTO
Todas concluídas	CONCLUIDO


📍 Essa lógica não pode ficar no service.


---

🔹 Regra 2 — Progresso do Projeto

O projeto deve ser capaz de responder:

int calcularProgresso()

Retorno:

percentual inteiro (0 a 100)

baseado em horas concluídas vs horas estimadas



---

🔹 Regra 3 — Enum com comportamento

Crie um enum:

StatusProjeto

Ele deve:

Conter os status

Ter um método getDescricao()

Ser usado diretamente no Projeto



---

🧱 Estrutura esperada (não copie, interprete)

model/
 ├── Projeto
 ├── Tarefa
 ├── StatusProjeto (enum)
 ├── TamanhoProjeto (continua existindo)
service/
 └── ProjetoService
Main


---

🚦 O que você NÃO deve fazer

❌ if/else no Service para status
❌ Status sendo digitado pelo usuário
❌ Projeto sem responsabilidade própria
❌ Retornar listas para o Service decidir lógica


---

🧪 Critério de sucesso

Seu sistema deve conseguir imprimir algo como:

Projeto: Sistema Financeiro
Status: Em andamento
Progresso: 65%
Tamanho: Médio


---

🧠 Dica final (não é solução)

> “Se o projeto sabe suas tarefas,
ele sabe se está concluído.”



Guarda essa frase.


---

Quando terminar:

envie somente as classes (model + service)

não envie o Main primeiro


A partir disso, a gente refatora como profissional 🔧🔥