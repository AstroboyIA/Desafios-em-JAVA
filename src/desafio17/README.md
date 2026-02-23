---

# 🧩 DESAFIO 17 — Pipeline de Deploy com Janela de Mudança

---

# 🎯 Objetivo

Construir um sistema orientado a objetos para controlar a execução de um deploy em produção, garantindo:

* Encapsulamento forte no model
* Regras de negócio implementadas no domínio (não no `Main`)
* Cálculos derivados consistentes
* Uso de enumerações com comportamento
* Ponderação por criticidade
* Execução demonstrativa via `Main`

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 PipelineDeploy

Representa uma execução de deploy em produção.

### Atributos obrigatórios

```java
private final String idExecucao;
private final String sistema;
private final int janelaMudancaMinutos;
private final List<EtapaDeploy> etapas;
```

---

## 🔒 Invariantes do Pipeline

1. `idExecucao` não pode ser nulo ou vazio.
2. `sistema` não pode ser nulo ou vazio.
3. `janelaMudancaMinutos` deve ser maior que 0.
4. A lista interna de etapas **não pode ser exposta de forma mutável**.
5. Etapas só podem ser adicionadas por método controlado.
6. Não é permitido remover etapas.

Violação → `IllegalArgumentException`.

---

## 📌 Adição de Etapas

```java
public void adicionarEtapa(EtapaDeploy etapa)
```

Regras:

* Não é permitido adicionar etapa `null`.
* Não é permitido adicionar etapa se o pipeline estiver `CONCLUIDO`.

Exceções:

* `etapa == null` → `IllegalArgumentException`
* Pipeline concluído → `IllegalStateException`

---

# 📌 EtapaDeploy

Representa uma fase do deploy.

### Atributos obrigatórios

```java
private final String nome;
private final int minutosEstimados;
private int minutosExecutados;
private final CriticidadeEtapa criticidade;
```

---

## 🔒 Invariantes da Etapa

1. `nome` não pode ser nulo ou vazio.
2. `minutosEstimados` deve ser maior que 0.
3. `minutosExecutados` deve ser maior ou igual a 0.
4. `minutosExecutados` não pode ser maior que `minutosEstimados`.
5. `criticidade` não pode ser nula.

Qualquer violação → `IllegalArgumentException`.

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Status do Pipeline

Enum obrigatório:

```java
StatusPipeline
```

Valores:

* ABERTO
* EM_EXECUCAO
* CONCLUIDO

Assinatura:

```java
public StatusPipeline getStatus()
```

### Lógica oficial (sem ambiguidades)

1. Se não houver etapas → `ABERTO`
2. Se todas as etapas tiverem `minutosExecutados == 0` → `ABERTO`
3. Se todas as etapas estiverem concluídas (`executado == estimado`) → `CONCLUIDO`
4. Caso contrário → `EM_EXECUCAO`

---

## 🔹 Regra 2 — Previsão Total Ponderada

```java
public int calcularPrevisaoTotalPonderada()
```

Fórmula:

```
sum(minutosEstimados * criticidade.getPeso())
```

Se não houver etapas → retornar `0`.

---

## 🔹 Regra 3 — Tempo Executado Total Ponderado

```java
public int calcularTempoExecutadoPonderado()
```

Fórmula:

```
sum(minutosExecutados * criticidade.getPeso())
```

Se não houver etapas → retornar `0`.

---

## 🔹 Regra 4 — Progresso do Deploy

```java
public int calcularProgressoDeploy()
```

Fórmula:

```
(executadoPonderado * 100) / previsaoPonderada
```

Regras obrigatórias:

* Se `previsaoPonderada == 0` → retornar `0`
* Divisão inteira
* Resultado limitado entre 0 e 100

---

## 🔹 Regra 5 — Situação da Janela de Mudança

Enum obrigatório:

```java
SituacaoJanela
```

Valores:

* DENTRO_DA_JANELA
* NO_LIMITE
* ESTOURADO

Assinatura:

```java
public SituacaoJanela getSituacaoJanela()
```

### Lógica

Baseada exclusivamente na **previsão total ponderada**:

* previsão < janelaMudancaMinutos → `DENTRO_DA_JANELA`
* previsão == janelaMudancaMinutos → `NO_LIMITE`
* previsão > janelaMudancaMinutos → `ESTOURADO`

---

## 🔹 Regra 6 — Índice de Consumo da Janela

```java
public int calcularIndiceConsumoJanela()
```

Fórmula:

```
(executadoPonderado * 100) / janelaMudancaMinutos
```

Regras:

* Se `janelaMudancaMinutos == 0` → retornar `0` (defensivo)
* Divisão inteira
* Resultado limitado entre 0 e 100

---

## 🔹 Regra 7 — Risco Operacional

Enum obrigatório:

```java
RiscoOperacionalDeploy
```

Valores:

* BAIXO
* MEDIO
* ALTO

Assinatura:

```java
public RiscoOperacionalDeploy getRiscoOperacional()
```

### Etapa 1 — Risco Base

* DENTRO_DA_JANELA → BAIXO
* NO_LIMITE → MEDIO
* ESTOURADO → ALTO

### Etapa 2 — Regra Adicional de Elevação

Se existir **etapa com criticidade ALTA não concluída**
E
`calcularIndiceConsumoJanela() >= 80`

Aplicar elevação de risco:

* BAIXO → MEDIO
* MEDIO → ALTO
* ALTO → permanece ALTO

---

# 📊 ENUMS OBRIGATÓRIOS

---

## CriticidadeEtapa

Deve ser um `enum`.

Valores obrigatórios:

* BAIXA
* MEDIA
* ALTA

Pesos obrigatórios:

* BAIXA → 1
* MEDIA → 2
* ALTA → 3

Deve conter:

```java
public int getPeso();
public String getDescricao();
```

---

## StatusPipeline, SituacaoJanela e RiscoOperacionalDeploy

Todos devem conter:

```java
public String getDescricao();
```

---

# 🧱 Estrutura de Pacotes Sugerida

```text
desafio17/
 ├── src/
 │    ├── main/java/desafio17/
 │    │    ├── model/
 │    │    │    ├── PipelineDeploy.java
 │    │    │    ├── EtapaDeploy.java
 │    │    │    ├── StatusPipeline.java
 │    │    │    ├── SituacaoJanela.java
 │    │    │    ├── RiscoOperacionalDeploy.java
 │    │    │    └── CriticidadeEtapa.java
 │    │    └── service/
 │    │         └── OrquestracaoDeployService.java
 │    └── Main.java
```

Pacotes:

```java
package desafio17.model;
package desafio17.service;
```

---

# 📌 Exemplo de Saída Esperada (Referência)

```
Pipeline: DEP-2026-091 | Sistema: Pagamentos
Status: Em execução
Progresso: 58%
Previsão ponderada: 290min
Janela de mudança: 240min
Situação da janela: Estourado
Risco operacional: Alto
Índice de consumo da janela: 76%
```

---

# 🚦 Restrições Importantes

❌ Não colocar regra de negócio no `Main`
❌ Não expor lista interna mutável
❌ Não usar `switch` externo para calcular risco
❌ Não calcular lógica no `service` que pertence ao domínio

---

# 🧠 Princípio Central do Desafio

> Se uma regra é importante para o negócio,
> ela deve estar protegida dentro do modelo de domínio.

---