
---

# 🧩 DESAFIO 15 — Sistema de Produção de Pedidos

---

# 🎯 Objetivo

Construir um sistema para acompanhar a produção diária de pedidos de uma confeitaria, garantindo:

* Encapsulamento forte no model
* Validação de invariantes no domínio
* Cálculos derivados consistentes
* Enum com comportamento para exibição amigável
* Ausência de regras duplicadas fora do model

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 PedidoProducao

Representa um pedido em produção.

### Atributos obrigatórios

* `String nomeCliente`
* `int tempoMetaMinutos`
* `List<EtapaProducao> etapas` (não pode ser exposta diretamente)

---

## 🔒 Invariantes do Pedido

1. `nomeCliente` não pode ser nulo ou vazio.
2. `tempoMetaMinutos` não pode ser negativo.
3. A lista interna de etapas não pode ser exposta de forma mutável.
4. Etapas só podem ser adicionadas através de método controlado.

---

## 📌 Regras sobre Etapas

* Etapas só podem ser adicionadas via:

```java
public void adicionarEtapa(EtapaProducao etapa)
```

* Não é permitido remover etapas.
* Não é permitido adicionar etapas após o pedido estar FINALIZADO.
* Não é permitido adicionar etapa nula.

---

# 📌 EtapaProducao

Representa uma etapa individual do pedido.

---

## 🔒 Invariantes da Etapa

1. `descricao` não pode ser nula ou vazia.
2. `tempoEstimadoMinutos` deve ser maior que 0.
3. `tempoExecutadoMinutos` deve ser ≥ 0.
4. `tempoExecutadoMinutos` não pode ser maior que `tempoEstimadoMinutos`.

Caso qualquer regra seja violada → lançar `IllegalArgumentException`.

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Status do Pedido

Enum:

```java
StatusPedido
```

Valores:

* NAO_INICIADO
* EM_PREPARO
* FINALIZADO

### Lógica

1. Sem etapas → NAO_INICIADO
2. Com etapas e totalExecutado = 0 → NAO_INICIADO
3. Pelo menos uma etapa com execução parcial → EM_PREPARO
4. Todas as etapas com execução total igual ao estimado → FINALIZADO

Status é sempre calculado dinamicamente (não armazenado).

---

## 🔹 Regra 2 — Previsão Total de Esforço

```java
int calcularPrevisaoTotalMinutos()
```

Lógica:

Somar todos os `tempoEstimadoMinutos`.

Se não houver etapas → retornar 0.

---

## 🔹 Regra 3 — Tempo Executado Total

```java
int calcularTempoExecutadoTotal()
```

Somar todos os `tempoExecutadoMinutos`.

Se não houver etapas → retornar 0.

---

## 🔹 Regra 4 — Progresso de Execução

```java
int calcularProgressoExecucao()
```

Fórmula:

```
(totalExecutado * 100) / totalEstimado
```

Regras adicionais:

* Se totalEstimado = 0 → retornar 0
* Resultado deve ser limitado entre 0 e 100
* Divisão inteira (truncada)

---

## 🔹 Regra 5 — Situação de Prazo

Enum:

```java
SituacaoPrazoPedido
```

Valores:

* ADIANTADO
* NO_PRAZO
* ATRASADO

### Lógica

Comparar:

```
previsaoTotalMinutos vs tempoMetaMinutos
```

* previsão < meta → ADIANTADO
* previsão == meta → NO_PRAZO
* previsão > meta → ATRASADO

---

## 🔹 Regra 6 — Índice Real de Cumprimento da Meta

```java
int calcularIndiceCumprimentoMeta()
```

Fórmula:

```
(tempoExecutadoTotal * 100) / tempoMetaMinutos
```

Regras adicionais:

* Se tempoMetaMinutos = 0 → retornar 0
* Resultado limitado entre 0 e 100
* Divisão inteira (truncada)

---

## 🔹 Regra 7 — Risco Operacional

Enum:

```java
RiscoOperacional
```

Valores:

* BAIXO
* MEDIO
* ALTO

Lógica:

* ADIANTADO → BAIXO
* NO_PRAZO → MEDIO
* ATRASADO → ALTO

Risco deve ser calculado dinamicamente a partir da situação de prazo.

Não pode ser armazenado como atributo fixo.

---

# 📊 Comportamento dos Enums

Cada enum deve possuir método:

```java
public String getDescricao()
```

Exemplo:

* NAO_INICIADO → "Não iniciado"
* EM_PREPARO → "Em preparo"
* FINALIZADO → "Finalizado"

O model nunca deve retornar texto hardcoded fora do enum.

---

# 🧱 Estrutura Esperada

```
model/
 ├── PedidoProducao
 ├── EtapaProducao
 ├── StatusPedido
 ├── SituacaoPrazoPedido
 ├── RiscoOperacional

service/
 └── ProducaoService

Main
```

---

# 🚦 Responsabilidade do Service

O `ProducaoService` pode:

* Criar pedidos
* Adicionar etapas
* Orquestrar chamadas
* Imprimir relatório

O service **não pode conter regra de negócio**.

---

# 🧪 Casos de Borda que Devem Funcionar

1. Pedido sem etapas
2. Meta igual a zero
3. Etapas cuja soma ultrapassa meta
4. Etapas totalmente executadas
5. Etapas parcialmente executadas
6. Execução total maior que meta (índice deve limitar a 100)

---

# 📌 Critério de Saída Esperado

Exemplo de saída:

```
Pedido: Bolo Aniversário - Cliente Marina
Status: Em preparo
Progresso: 55%
Previsão total: 180min
Meta planejada: 150min
Situação de prazo: Atrasado
Risco operacional: Alto
Índice de cumprimento da meta: 66%
```