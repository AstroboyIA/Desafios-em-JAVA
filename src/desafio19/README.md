Aqui você vai trabalhar com:

* Autorização financeira
* Antifraude baseado em múltiplos critérios
* Score dinâmico
* Estados derivados
* Penalidade por reincidência
* Bloqueio automático
* Enum com comportamento
* Regras cumulativas e condicionais
* Imutabilidade parcial

Este já é nível próximo de sistema bancário real.

---

# 💳 DESAFIO 19 — Motor de Autorização de Transações Financeiras com Antifraude

---

# 🎯 Objetivo

Construir um motor orientado a objetos capaz de:

* Autorizar ou negar transações financeiras
* Calcular score antifraude
* Bloquear automaticamente cartão por risco extremo
* Controlar reincidência suspeita
* Derivar status dinamicamente
* Aplicar penalidades por comportamento suspeito
* Manter histórico imutável de análises

Nenhuma regra no `Main`.

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 TransacaoFinanceira

Representa uma tentativa de compra.

### Atributos obrigatórios

```java
private final String id;
private final double valor;
private final CategoriaTransacao categoria;
private final CanalTransacao canal;
private final int minutoDoDia;
private final ContaCartao conta;
```

---

# 🔒 Invariantes

1. `id` não pode ser nulo ou vazio
2. `valor` > 0
3. `categoria` não pode ser nula
4. `canal` não pode ser nulo
5. `minutoDoDia` entre 0 e 1440
6. `conta` não pode ser nula

Violação → IllegalArgumentException

---

## 📌 ContaCartao

Representa o portador.

```java
private final String numero;
private final PerfilRiscoCliente perfilRisco;
private final double limite;
private double saldoUtilizado;
private boolean bloqueado;
private final List<AnaliseAntifraude> historicoAnalises;
private int tentativasSuspeitas;
```

---

# 🔒 Invariantes

1. número não nulo
2. limite > 0
3. saldoUtilizado ≥ 0
4. saldoUtilizado ≤ limite
5. histórico não exposto mutável

---

# 📊 ENUMS OBRIGATÓRIOS

---

## CategoriaTransacao

Valores exemplo:

* ALIMENTACAO
* ELETRONICOS
* JOIAS
* SERVICOS
* INTERNACIONAL

Cada categoria deve conter:

```java
public int getPesoRisco();
```

Exemplo:

* ALIMENTACAO → 1
* ELETRONICOS → 2
* JOIAS → 4
* SERVICOS → 1
* INTERNACIONAL → 5

---

## CanalTransacao

Valores:

* PRESENCIAL
* ONLINE
* APP
* INTERNACIONAL_ONLINE

Cada um deve conter:

```java
public int getPesoRiscoCanal();
```

---

## PerfilRiscoCliente

Valores:

* BAIXO
* MODERADO
* ALTO

Cada um deve conter:

```java
public int getMultiplicadorRisco();
```

---

## StatusAutorizacao

Valores:

* APROVADA
* NEGADA
* BLOQUEADA

---

## NivelFraude

Valores:

* NORMAL
* SUSPEITA
* ALTO_RISCO
* FRAUDE_CONFIRMADA

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Score Antifraude

Criar método:

```java
public int calcularScoreAntifraude()
```

Fórmula base:

```
(valor / 100)
+ categoria.getPesoRisco()
+ canal.getPesoRiscoCanal()
```

Multiplicar pelo:

```
perfilRisco.getMultiplicadorRisco()
```

Se:

* minutoDoDia entre 0–300 → +5 pontos (horário crítico)

Resultado limitado entre 0–100.

---

## 🔹 Regra 2 — Classificação de Fraude

```java
public NivelFraude classificarFraude()
```

Score:

* <20 → NORMAL
* 20–39 → SUSPEITA
* 40–69 → ALTO_RISCO
* ≥70 → FRAUDE_CONFIRMADA

---

## 🔹 Regra 3 — Autorização da Transação

```java
public StatusAutorizacao autorizar()
```

Ordem obrigatória:

1. Se conta bloqueada → BLOQUEADA
2. Se valor ultrapassa limite → NEGADA
3. Se fraude confirmada → BLOQUEADA e bloquear conta
4. Se alto risco → NEGADA e incrementar tentativasSuspeitas
5. Se suspeita → APROVADA mas registrar tentativa
6. Caso contrário → APROVADA

---

## 🔹 Regra 4 — Bloqueio Automático por Reincidência

Se `tentativasSuspeitas >= 3`:

→ bloquear conta automaticamente

---

## 🔹 Regra 5 — Histórico de Análises

Criar classe:

## AnaliseAntifraude

```java
private final int score;
private final NivelFraude nivel;
private final StatusAutorizacao status;
private final double valor;
```

Cada autorização deve registrar análise.

Lista deve ser imutável externamente.

---

## 🔹 Regra 6 — Índice de Exposição Financeira

```java
public double calcularIndiceExposicao()
```

Fórmula:

```
saldoUtilizado / limite * 100
```

Limitado 0–100.

---

## 🔹 Regra 7 — Grau de Criticidade da Conta

Criar enum:

## CriticidadeConta

* ESTAVEL
* MONITORADA
* CRITICA
* BLOQUEADA

Baseado em:

* tentativasSuspeitas
* nivelFraude da última análise
* índice de exposição

Exemplo:

Se bloqueada → BLOQUEADA
Se fraude confirmada ou exposição ≥ 90% → CRITICA
Se tentativas ≥ 2 → MONITORADA
Senão → ESTAVEL

---

# 🚫 Restrições

❌ Não armazenar score fixo
❌ Não usar switch fora dos enums
❌ Não permitir desbloquear manualmente
❌ Não expor histórico mutável
❌ Não permitir alterar limite após criação

---

# 📌 Exemplo de Execução

```
Transação: R$ 2.000
Categoria: JOIAS
Canal: INTERNACIONAL_ONLINE
Perfil: ALTO
Horário: 02:30

Score: 78
Classificação: FRAUDE_CONFIRMADA
Status: BLOQUEADA
Conta: BLOQUEADA
Tentativas suspeitas: 1
Criticidade da conta: BLOQUEADA
```

---

# 🧠 O que este desafio testa

* Modelagem de risco composta
* Ordem crítica de validação
* Estado mutável controlado
* Bloqueio automático
* Reincidência
* Enum com comportamento
* Registro imutável
* Cálculo financeiro proporcional

---

# 📊 Nível de Complexidade

Desafio 18 → Regras operacionais
Desafio 19 → Motor antifraude realista

Este já se aproxima de backend bancário simplificado.

---

Se você concluir esse com 100%, o próximo nível será:

🔥 Simulação concorrente com múltiplas transações simultâneas
🔥 Rate limiting e janela temporal
🔥 Agregador estatístico de fraude
🔥 Sistema distribuído simplificado

Quando começar, posso revisar sua arquitetura antes da implementação.
