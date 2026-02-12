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

---

🌐 MÓDULO FRONTEND (primeira prática integrada)

Objetivo deste módulo:

Começar frontend sem abandonar Java.
Aqui o frontend vai servir para visualizar o mesmo domínio do desafio 13 (Projeto, Tarefa, Status, Progresso).

Importante:
Este frontend não substitui seu backend Java.
Ele é um treino paralelo para você praticar interface, manipulação de dados e lógica no navegador.

---

✅ O que construir no frontend

Crie uma página simples chamada "Acompanhamento de Projeto".
Ela precisa ter:

1. Campo para nome do projeto
2. Formulário para cadastrar tarefas com:
   - descrição
   - horas estimadas
   - horas concluídas
3. Lista de tarefas cadastradas
4. Resumo do projeto exibindo:
   - Status do projeto
   - Progresso (%) 
   - Tamanho do projeto

---

🧱 Estrutura sugerida de arquivos (frontend)

Dentro de `src/desafio13/`, crie uma pasta:

frontend/
 ├── index.html
 ├── style.css
 └── app.js

---

🎯 Regras do frontend (as mesmas ideias do Java)

Você deve reaplicar no JavaScript as mesmas regras de domínio:

1. Status:
   - Sem tarefas -> NAO_INICIADO
   - Algumas concluídas -> EM_ANDAMENTO
   - Todas concluídas -> CONCLUIDO

2. Progresso:
   - progresso = (horasConcluidas / horasEstimadas) * 100
   - resultado inteiro entre 0 e 100

3. Conclusão da tarefa:
   - tarefa concluída quando horasConcluidas >= horasEstimadas

4. Tamanho do projeto:
   - até 20h -> Pequeno
   - até 50h -> Médio
   - acima de 50h -> Grande

---

🧪 Fluxo esperado da tela

1. Usuário informa nome do projeto.
2. Usuário adiciona tarefas.
3. Ao adicionar tarefa:
   - tarefa entra na lista visual
   - resumo atualiza automaticamente
4. O resumo sempre deve mostrar o estado atual real do projeto.

---

🛠️ Requisitos técnicos mínimos (para não se perder)

HTML:
- Estruture com `form`, `input`, `button`, `section`.
- Tenha uma área para lista de tarefas e outra para resumo.

CSS:
- Organize layout centralizado.
- Destaque visual para status (ex.: cores diferentes para cada status).
- Mostre progresso com barra simples (`div` com largura em %).

JavaScript:
- Use um array de tarefas em memória.
- Crie funções separadas:
  - `adicionarTarefa()`
  - `calcularStatusProjeto()`
  - `calcularProgressoProjeto()`
  - `calcularTamanhoProjeto()`
  - `renderizarTela()`
- Evite lógica duplicada.

---

📌 O que NÃO fazer no frontend

❌ Não usar framework agora (React/Vue/etc.)
❌ Não usar backend neste primeiro passo
❌ Não misturar toda lógica dentro de um único bloco gigante
❌ Não deixar regra de negócio espalhada em vários `if` sem função dedicada

---

✅ Critério de sucesso do módulo frontend

Ao abrir `index.html`, você deve conseguir:

1. Cadastrar tarefas
2. Ver lista atualizada
3. Ver status correto
4. Ver progresso correto
5. Ver tamanho correto

Se isso funcionar, seu primeiro ciclo full-stack está iniciado com qualidade.

---

🚀 Próximo passo depois de finalizar

Quando concluir essa primeira versão:

1. Refatore o JavaScript para ficar mais limpo
2. Compare as regras do `Projeto` no Java com as funções do frontend
3. Garanta que o mesmo cenário gera o mesmo resultado nas duas camadas

Esse alinhamento de regra entre backend e frontend é habilidade central de dev full-stack.
