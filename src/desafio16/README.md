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

### 📎 Contrato de exceções (obrigatório)

* Ao tentar adicionar atividade `null` -> lançar `IllegalArgumentException`.
* Ao tentar adicionar atividade com chamado já `RESOLVIDO` -> lançar `IllegalStateException`.

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

Assinatura obrigatória:

```java
public StatusChamado getStatus()
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

Assinatura obrigatória:

```java
public SituacaoSLA getSituacaoSLA()
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

Observação de consistência:

* O construtor de `ChamadoSuporte` deve validar `slaMinutos > 0`.
* A regra `slaMinutos = 0 -> 0` é defensiva (para evitar erro de divisão), mesmo não sendo cenário válido de criação.

---

## 🔹 Regra 7 — Risco de Atendimento

Enum:

```java
RiscoAtendimento
```

Assinatura obrigatória:

```java
public RiscoAtendimento getRiscoAtendimento()
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

### Estrutura de domínio (conceitual)

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

### Estrutura usada neste repositório

```text
desafio16/
 ├── main/
 │    ├── model/
 │    │    ├── ChamadoSuporte.java
 │    │    ├── AtividadeAtendimento.java
 │    │    ├── StatusChamado.java
 │    │    ├── SituacaoSLA.java
 │    │    └── RiscoAtendimento.java
 │    └── service/
 │         └── AtendimentoService.java
 └── micro-testes/
      └── src/test/java/desafio16/model/
           ├── ChamadoSuporteTest.java
           └── AtividadeAtendimentoTest.java
```

Pacotes esperados para o domínio:

```java
package main.desafio16.model;
```

Pacote dos testes:

```java
package desafio16.model;
```

Com imports explícitos para as classes do domínio.

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
