---

# 🧩 DESAFIO 16 — Sistema de Triagem de Chamados (Help Desk)

---

# 🎯 Objetivo

Construir um sistema para acompanhar o ciclo de atendimento de chamados técnicos de um service desk, garantindo:

* Encapsulamento forte no model
* Regras de negócio centralizadas no domínio
* Cálculos derivados consistentes (progresso, SLA, risco)
* Enum com comportamento para exibição amigável
* Testes automatizados com **JUnit 5** como parte obrigatória da entrega

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 ChamadoSuporte

Representa um chamado aberto por um cliente.

### Atributos obrigatórios

* `String protocolo`
* `String cliente`
* `int slaMinutos` (tempo limite para atendimento)
* `List<AtividadeAtendimento> atividades` (não pode ser exposta diretamente)

---

## 🔒 Invariantes do Chamado

1. `protocolo` não pode ser nulo ou vazio.
2. `cliente` não pode ser nulo ou vazio.
3. `slaMinutos` deve ser maior que 0.
4. A lista interna de atividades não pode ser exposta de forma mutável.
5. Atividades só podem ser adicionadas por método controlado.

---

## 📌 Regras sobre Atividades

* Atividades só podem ser adicionadas via:

```java
public void adicionarAtividade(AtividadeAtendimento atividade)
```

* Não é permitido adicionar atividade nula.
* Não é permitido adicionar atividade após o chamado estar RESOLVIDO.
* Não é permitido remover atividade.

---

# 📌 AtividadeAtendimento

Representa uma etapa de trabalho dentro do chamado.

### Atributos obrigatórios

* `String descricao`
* `int minutosEstimados`
* `int minutosExecutados`

---

## 🔒 Invariantes da Atividade

1. `descricao` não pode ser nula ou vazia.
2. `minutosEstimados` deve ser maior que 0.
3. `minutosExecutados` deve ser maior ou igual a 0.
4. `minutosExecutados` não pode ser maior que `minutosEstimados`.

Caso qualquer regra seja violada → lançar `IllegalArgumentException`.

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Status do Chamado

Enum:

```java
StatusChamado
```

Valores:

* ABERTO
* EM_ATENDIMENTO
* RESOLVIDO

### Lógica

1. Sem atividades → ABERTO
2. Com atividades e total executado = 0 → ABERTO
3. Com pelo menos uma atividade não concluída → EM_ATENDIMENTO
4. Com todas as atividades concluídas → RESOLVIDO

Status é calculado dinamicamente (não armazenado como campo fixo).

---

## 🔹 Regra 2 — Previsão Total de Atendimento

```java
int calcularPrevisaoTotalMinutos()
```

Lógica:

Somar todos os `minutosEstimados`.

Se não houver atividades → retornar 0.

---

## 🔹 Regra 3 — Tempo Executado Total

```java
int calcularTempoExecutadoTotal()
```

Lógica:

Somar todos os `minutosExecutados`.

Se não houver atividades → retornar 0.

---

## 🔹 Regra 4 — Progresso do Chamado

```java
int calcularProgressoAtendimento()
```

Fórmula:

```
(totalExecutado * 100) / totalEstimado
```

Regras adicionais:

* Se `totalEstimado = 0` → retornar 0
* Resultado deve ser limitado entre 0 e 100
* Divisão inteira (truncada)

---

## 🔹 Regra 5 — Situação de SLA

Enum:

```java
SituacaoSLA
```

Valores:

* DENTRO_DO_SLA
* NO_LIMITE
* ESTOURADO

### Lógica

Comparar:

```
previsaoTotalMinutos vs slaMinutos
```

* previsão < SLA → DENTRO_DO_SLA
* previsão == SLA → NO_LIMITE
* previsão > SLA → ESTOURADO

---

## 🔹 Regra 6 — Índice de Consumo de SLA

```java
int calcularIndiceConsumoSLA()
```

Fórmula:

```
(tempoExecutadoTotal * 100) / slaMinutos
```

Regras adicionais:

* Se `slaMinutos = 0` → retornar 0
* Resultado limitado entre 0 e 100
* Divisão inteira (truncada)

---

## 🔹 Regra 7 — Risco de Atendimento

Enum:

```java
RiscoAtendimento
```

Valores:

* BAIXO
* MEDIO
* ALTO

Lógica:

* DENTRO_DO_SLA → BAIXO
* NO_LIMITE → MEDIO
* ESTOURADO → ALTO

Risco deve ser calculado dinamicamente a partir da situação de SLA.

---

# 📊 Comportamento dos Enums

Cada enum deve possuir método:

```java
public String getDescricao()
```

Exemplos:

* ABERTO → "Aberto"
* EM_ATENDIMENTO → "Em atendimento"
* RESOLVIDO → "Resolvido"

Não retornar texto hardcoded em service/Main.

---

# 🧱 Estrutura Esperada

```
model/
 ├── ChamadoSuporte
 ├── AtividadeAtendimento
 ├── StatusChamado
 ├── SituacaoSLA
 ├── RiscoAtendimento

service/
 └── AtendimentoService

Main
```

---

# ✅ Próximo Passo Integrado: Testes Automatizados (JUnit 5)

Além do sistema funcionando no `Main`, você deve entregar testes automatizados.

## Dependências mínimas

* JUnit Jupiter (`junit-jupiter`)
* Plugin Surefire configurado para rodar testes

## Estrutura de testes esperada

```
src/test/java/desafio16/model/
 ├── ChamadoSuporteTest
 └── AtividadeAtendimentoTest
```

---

## 🧪 Casos mínimos obrigatórios (JUnit 5)

### `AtividadeAtendimentoTest`

1. Deve criar atividade válida.
2. Deve lançar exceção para descrição vazia.
3. Deve lançar exceção para estimado <= 0.
4. Deve lançar exceção para executado < 0.
5. Deve lançar exceção para executado > estimado.

### `ChamadoSuporteTest`

6. Deve criar chamado válido.
7. Deve lançar exceção para SLA <= 0.
8. Sem atividades, status deve ser ABERTO.
9. Com atividades parciais, status deve ser EM_ATENDIMENTO.
10. Com atividades completas, status deve ser RESOLVIDO.
11. Situação de SLA deve ser DENTRO_DO_SLA.
12. Situação de SLA deve ser NO_LIMITE.
13. Situação de SLA deve ser ESTOURADO.
14. Índice de consumo deve limitar em 100.
15. Não deve permitir adicionar atividade com chamado resolvido.

---

# 🚦 O que você NÃO deve fazer

❌ Colocar regra de status/SLA/risco no service
❌ Fazer validação principal no Main
❌ Expor lista interna mutável
❌ Fazer apenas teste manual no terminal
❌ Entregar sem `src/test` com JUnit 5

---

# 📌 Critério de Saída Esperado

Exemplo de saída:

```
Chamado: CH-2026-1042 - Cliente ACME
Status: Em atendimento
Progresso: 60%
Previsão total: 200min
SLA planejado: 180min
Situação SLA: Estourado
Risco de atendimento: Alto
Índice de consumo do SLA: 72%
```

---

# 🧠 Dica final (não é solução)

> "Se a regra é importante para o negócio,
> ela precisa estar protegida por teste automatizado."

---