
---

# 🛒 DESAFIO 20 — Sistema de Gestão de Estoque com Notificações e Estratégias de Precificação

---

# 🎯 Objetivo

Construir um sistema orientado a objetos capaz de:

* Gerenciar estoque de produtos com tipos variados usando **Generics**
* Aplicar diferentes estratégias de precificação usando **Strategy**
* Notificar interessados sobre eventos de estoque usando **Observer**
* Modelar comportamentos distintos por tipo de produto usando **Interfaces + Polimorfismo**

Nenhuma regra no `Main`.

---

# 🏗️ MODELO DE DOMÍNIO

---

## 📌 Produto\<T extends DadosAdicionais\>

Classe genérica que representa qualquer produto do catálogo.

```java
private final String id;
private final String nome;
private final CategoriaEstoque categoria;
private double precoBase;
private int quantidadeEmEstoque;
private final T dadosAdicionais;
```

### Invariantes

1. `id` não nulo ou vazio
2. `nome` não nulo ou vazio
3. `precoBase` > 0
4. `quantidadeEmEstoque` >= 0
5. `dadosAdicionais` não nulo

Violação → `IllegalArgumentException`

---

## 📌 Interface DadosAdicionais

```java
public interface DadosAdicionais {
    String resumo();
}
```

Implementações obrigatórias:

### DadosFisicos

```java
private final double pesoKg;
private final String dimensoesCm; // ex: "30x20x10"
private final boolean requerRefrigeracao;
```

### DadosDigitais

```java
private final String urlDownload;
private final double tamanhoMb;
private final String plataforma;
```

### DadosPerecivel

```java
private final LocalDate dataValidade;
private final String loteRastreamento;
```

---

## 📌 Interface Precificavel

```java
public interface Precificavel {
    double calcularPrecoFinal(double precoBase);
    String descricaoEstrategia();
}
```

---

## 📌 Interface Observador

```java
public interface Observador {
    void notificar(EventoEstoque evento);
}
```

---

## 📌 EventoEstoque

```java
private final TipoEvento tipo;
private final String idProduto;
private final String nomeProduto;
private final int quantidadeAnterior;
private final int quantidadeAtual;
private final LocalDateTime momento;
```

Imutável. Sem setters.

---

## 📌 GerenciadorEstoque

Classe central do sistema.

```java
private final Map<String, Produto<?>> catalogo;
private final List<Observador> observadores;
private Precificavel estrategiaPrecificacao;
```

### Comportamentos

```java
public <T extends DadosAdicionais> void cadastrarProduto(Produto<T> produto);
public void entradaEstoque(String idProduto, int quantidade);
public void saidaEstoque(String idProduto, int quantidade);
public void alterarEstrategia(Precificavel novaEstrategia);
public void registrarObservador(Observador observador);
public void removerObservador(Observador observador);
public double consultarPreco(String idProduto);
public RelatorioEstoque gerarRelatorio();
```

---

## 📌 RelatorioEstoque

```java
private final int totalProdutos;
private final int produtosAbaixoDoMinimo;
private final int produtosSemEstoque;
private final double valorTotalEmEstoque;
private final Map<CategoriaEstoque, Integer> quantidadePorCategoria;
private final List<String> alertasValidade; // apenas DadosPerecivel vencendo em 7 dias
```

Imutável. **Builder obrigatório.**

---

# 📊 ENUMS OBRIGATÓRIOS

---

## CategoriaEstoque

```java
public enum CategoriaEstoque {
    ELETRONICO,
    ALIMENTICIO,
    VESTUARIO,
    DIGITAL,
    PERECIVEL;

    public int getQuantidadeMinimaRecomendada();
}
```

Exemplo de mínimos:

* ELETRONICO → 5
* ALIMENTICIO → 20
* VESTUARIO → 10
* DIGITAL → 0 (estoque ilimitado)
* PERECIVEL → 15

---

## TipoEvento

```java
public enum TipoEvento {
    PRODUTO_CADASTRADO,
    ENTRADA_ESTOQUE,
    SAIDA_ESTOQUE,
    ESTOQUE_MINIMO_ATINGIDO,
    ESTOQUE_ZERADO,
    VALIDADE_PROXIMA;

    public String getDescricao();
}
```

---

# 🧠 ESTRATÉGIAS DE PRECIFICAÇÃO (Strategy)

Cada uma implementa `Precificavel`:

---

## PrecoNormal

Retorna o precoBase sem alteração.

---

## PrecoComDesconto

```java
private final double percentualDesconto; // 0.0 a 1.0
```

```
precoFinal = precoBase * (1 - percentualDesconto)
```

Invariante: percentual entre 0 e 1, senão `IllegalArgumentException`.

---

## PrecoComMarkup

```java
private final double percentualMarkup;
```

```
precoFinal = precoBase * (1 + percentualMarkup)
```

---

## PrecoDinamico

```java
private final int estoqueAlvo;
```

Se `quantidadeEmEstoque <= estoqueAlvo`:

```
precoFinal = precoBase * 1.30  // escassez → +30%
```

Senão:

```
precoFinal = precoBase * 0.90  // excesso → -10%
```

> **Atenção:** `PrecoDinamico` precisa consultar o estoque atual do produto. Pense em como modelar isso sem quebrar o encapsulamento.

---

# 🧠 OBSERVADORES (Observer)

Cada um implementa `Observador`:

---

## LogObservador

Imprime no console uma linha formatada a cada evento recebido.

Exemplo:

```
[2025-06-01 14:32] ESTOQUE_ZERADO — Produto: Notebook Gamer (ID: P001) | Anterior: 1 | Atual: 0
```

---

## AlertaReposicaoObservador

Só age nos eventos `ESTOQUE_MINIMO_ATINGIDO` e `ESTOQUE_ZERADO`.

```java
private final List<String> alertasGerados;
```

Registra internamente uma mensagem de alerta e disponibiliza via:

```java
public List<String> getAlertas(); // imutável
```

---

## RelatorioEventosObservador

Acumula todos os eventos recebidos.

```java
private final List<EventoEstoque> eventos;

public List<EventoEstoque> getEventos(); // imutável
public long contarEventosPorTipo(TipoEvento tipo);
```

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Entrada de Estoque

1. Validar que `quantidade > 0`, senão `IllegalArgumentException`
2. Validar que produto existe, senão `NoSuchElementException`
3. Atualizar `quantidadeEmEstoque`
4. Emitir evento `ENTRADA_ESTOQUE`

---

## 🔹 Regra 2 — Saída de Estoque

1. Validar que `quantidade > 0`
2. Validar que produto existe
3. Validar que há estoque suficiente, senão `IllegalStateException`
4. Atualizar `quantidadeEmEstoque`
5. Emitir `SAIDA_ESTOQUE`
6. Se quantidade resultante == 0 → emitir também `ESTOQUE_ZERADO`
7. Se quantidade resultante > 0 e <= mínimo da categoria → emitir também `ESTOQUE_MINIMO_ATINGIDO`

---

## 🔹 Regra 3 — Notificação de Observadores

Ao emitir qualquer evento, o `GerenciadorEstoque` deve notificar **todos os observadores registrados** na ordem em que foram adicionados.

---

## 🔹 Regra 4 — Consulta de Preço

```java
public double consultarPreco(String idProduto)
```

Aplica a estratégia de precificação **atualmente configurada** sobre o `precoBase` do produto.

Produto não encontrado → `NoSuchElementException`.

---

## 🔹 Regra 5 — Alerta de Validade

Ao gerar o `RelatorioEstoque`, verificar todos os produtos com `DadosPerecivel`.

Se `dataValidade` for nos próximos **7 dias** a partir de hoje → incluir no campo `alertasValidade` e emitir evento `VALIDADE_PROXIMA`.

---

## 🔹 Regra 6 — Valor Total em Estoque

```
valorTotal = Σ (precoBase * quantidadeEmEstoque) para todos os produtos
```

Usar `precoBase`, não o preço com estratégia aplicada.

---

# 🚫 Restrições

❌ Não usar `instanceof` para desviar lógica — use polimorfismo
❌ Não expor listas internas mutáveis
❌ Não aplicar estratégia de precificação diretamente no `Produto`
❌ `RelatorioEstoque` somente via Builder
❌ Nenhuma regra de negócio no `Main`

---

# 📌 Exemplo de Execução

```
Cadastro: Notebook Gamer | ELETRONICO | R$3.000 | Qtd: 10
Cadastro: Iogurte Natural | PERECIVEL | R$5 | Qtd: 2 | Validade: 3 dias
Cadastro: Curso Java | DIGITAL | R$150 | Qtd: 999

Observadores: LogObservador, AlertaReposicaoObservador

Saída: Notebook Gamer x6
  → SAIDA_ESTOQUE (10 → 4)
  → ESTOQUE_MINIMO_ATINGIDO (mínimo ELETRONICO = 5)
  → Alerta gerado: "Repor Notebook Gamer — abaixo do mínimo"

Estratégia alterada: PrecoComDesconto(0.15)
Preço Notebook: R$2.550,00

--- Relatório ---
Total de produtos: 3
Abaixo do mínimo: 1 (Notebook Gamer)
Sem estoque: 0
Valor total em estoque: R$31.985,00
Alertas de validade: ["Iogurte Natural vence em 3 dias — Lote: L2024A"]
```

---

# 🧠 O que este desafio testa

* **Generics** — `Produto<T>` com bound `T extends DadosAdicionais`
* **Interfaces + Polimorfismo** — `DadosAdicionais`, `Precificavel`, `Observador` com múltiplas implementações
* **Strategy** — troca de estratégia de precificação em tempo de execução
* **Observer** — notificação desacoplada de múltiplos interessados
* **Builder** — construção controlada de `RelatorioEstoque`
* **Encapsulamento** — listas imutáveis, invariantes rigorosas
* **Modelagem de domínio** — tipos heterogêneos com comportamento coeso

---

# 📊 Nível de Complexidade

| Desafio | Foco principal |
|---------|----------------|
| 19 | Motor antifraude + enums com comportamento |
| **20** | **Generics + Strategy + Observer + Polimorfismo** |
| 21 | A definir com você 🚀 |