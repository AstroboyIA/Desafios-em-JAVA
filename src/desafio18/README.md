---

# 🧩 DESAFIO 18 — Gestão de Incidente Crítico com SLA Dinâmico

---

# 🎯 Objetivo

Construir um sistema orientado a objetos para controlar o ciclo de vida de um **Incidente Operacional Crítico**, garantindo:

* Encapsulamento rigoroso
* Estado derivado (sem setters de status)
* SLA dinâmico baseado em severidade
* Penalização por atraso progressiva
* Regras condicionais dependentes de múltiplos fatores
* Enumerações com comportamento
* Cálculos não triviais
* Nenhuma regra no `Main`

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 IncidenteOperacional

Representa um incidente aberto em ambiente produtivo.

### Atributos obrigatórios

```java
private final String codigo;
private final String servicoAfetado;
private final SeveridadeIncidente severidade;
private final int tempoSlaMinutos;
private final List<AcaoResposta> acoes;
private final int tempoDecorridoMinutos;
```

---

# 🔒 Invariantes

1. `codigo` não pode ser nulo ou vazio.
2. `servicoAfetado` não pode ser nulo ou vazio.
3. `severidade` não pode ser nula.
4. `tempoSlaMinutos` deve ser > 0.
5. `tempoDecorridoMinutos` deve ser ≥ 0.
6. Lista interna de ações não pode ser exposta mutável.
7. Não é permitido remover ações.
8. Não é permitido adicionar ação se incidente estiver encerrado.

Violação → `IllegalArgumentException`
Regra de estado → `IllegalStateException`

---

# 📌 AcaoResposta

Representa uma ação executada para mitigar o incidente.

### Atributos obrigatórios

```java
private final String descricao;
private final int minutosPlanejados;
private int minutosExecutados;
private final TipoAcao tipo;
```

---

# 🔒 Invariantes da Ação

1. `descricao` não pode ser nula ou vazia.
2. `minutosPlanejados` > 0.
3. `minutosExecutados` ≥ 0.
4. `minutosExecutados` ≤ `minutosPlanejados`.
5. `tipo` não pode ser nulo.

---

# 📊 ENUMS OBRIGATÓRIOS

---

## SeveridadeIncidente

Valores:

* BAIXA
* MODERADA
* ALTA
* CRITICA

Cada severidade deve conter:

```java
public int getMultiplicadorPenalidade();
public String getDescricao();
```

Multiplicadores obrigatórios:

* BAIXA → 1
* MODERADA → 2
* ALTA → 3
* CRITICA → 5

---

## TipoAcao

Valores:

* CONTENCAO
* MITIGACAO
* CORRECAO
* COMUNICACAO

Cada tipo deve conter:

```java
public int getPesoImpacto();
public boolean reduzRisco();
```

Exemplo de pesos:

* CONTENCAO → 3
* MITIGACAO → 2
* CORRECAO → 4
* COMUNICACAO → 1

---

## StatusIncidente

Valores:

* ABERTO
* EM_TRATAMENTO
* RESOLVIDO
* ENCERRADO

Deve conter:

```java
public String getDescricao();
```

---

## NivelRiscoIncidente

Valores:

* CONTROLADO
* ATENCAO
* CRITICO
* COLAPSO

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Status Derivado

```java
public StatusIncidente getStatus()
```

Lógica:

1. Sem ações → ABERTO
2. Se todas ações têm `minutosExecutados == 0` → ABERTO
3. Se todas ações concluídas → RESOLVIDO
4. Se tempoDecorridoMinutos > tempoSlaMinutos → ENCERRADO
5. Caso contrário → EM_TRATAMENTO

⚠ Ordem importa.

---

## 🔹 Regra 2 — Índice de Execução Ponderado

```java
public int calcularIndiceExecucao()
```

Fórmula:

```
sum(minutosExecutados * tipo.getPesoImpacto()) /
sum(minutosPlanejados * tipo.getPesoImpacto()) * 100
```

Regras:

* Se não houver ações → 0
* Divisão inteira
* Resultado limitado entre 0–100

---

## 🔹 Regra 3 — Penalidade por Atraso

```java
public int calcularPenalidade()
```

Se tempoDecorrido ≤ SLA → 0

Se ultrapassar SLA:

```
(atrasoMinutos * severidade.getMultiplicadorPenalidade())
```

---

## 🔹 Regra 4 — Grau de Saturação Operacional

```java
public int calcularSaturacao()
```

Fórmula:

```
(tempoDecorridoMinutos * 100) / tempoSlaMinutos
```

Limitado entre 0–200

Sim, pode passar de 100.

---

## 🔹 Regra 5 — Nível de Risco do Incidente

```java
public NivelRiscoIncidente getNivelRisco()
```

### Etapa 1 — Risco Base por Saturação

* < 70 → CONTROLADO
* 70–99 → ATENCAO
* 100–149 → CRITICO
* ≥150 → COLAPSO

### Etapa 2 — Agravante por Severidade

Se severidade for CRITICA e saturação ≥ 90
→ elevar risco em 1 nível

### Etapa 3 — Redutor por Ação Estratégica

Se existir pelo menos uma ação:

* do tipo CORRECAO
* concluída
* e que `reduzRisco()` seja true

→ reduzir risco em 1 nível (mínimo CONTROLADO)

---

# 🔹 Regra 6 — Índice de Eficiência

```java
public int calcularIndiceEficiencia()
```

Fórmula:

```
indiceExecucao - saturacao
```

Limitado entre -100 e 100

---

# 🚦 Restrições

❌ Não usar status armazenado
❌ Não calcular risco no `Main`
❌ Não usar `switch` fora dos enums
❌ Não expor lista mutável
❌ Não duplicar regra em service

---

# 🧱 Estrutura Sugerida

```text
desafio18/
 ├── model/
 │    ├── IncidenteOperacional.java
 │    ├── AcaoResposta.java
 │    ├── SeveridadeIncidente.java
 │    ├── TipoAcao.java
 │    ├── StatusIncidente.java
 │    └── NivelRiscoIncidente.java
 │
 ├───service/
 │    └──GestaoIncidenteService.java
 │
 └── Main.java
```

---

# 📌 Exemplo de Saída Esperada

```
Incidente: INC-9031
Serviço afetado: Gateway de Pagamento
Severidade: Crítica
Status: Em tratamento
Índice de execução: 62%
Saturação operacional: 118%
Penalidade acumulada: 90
Nível de risco: Crítico
Índice de eficiência: -56
```

---