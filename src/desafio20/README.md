
---

# 🛒 DESAFIO 20 — Sistema de Gestão de Estoque (Arquitetura Spring-Style)

---

# 🎯 Objetivo

Construir um sistema de e-commerce com **arquitetura em camadas**, simulando os padrões do Spring Boot em Java puro:

* Camadas separadas: **Service**, **Repository**, **Domain**
* **DTOs** de entrada e saída desacoplados do domínio
* **Injeção de dependência via construtor** (sem `new` dentro de classes)
* **Eventos e Listeners** desacoplados (Observer)
* **Generics**, **Strategy** e **Polimorfismo**

Nenhuma regra no `Main`. O `Main` apenas monta as dependências e chama o serviço.

---

# 🏗️ ESTRUTURA DE PACOTES

```
src/
├── domain/
│   ├── Produto.java
│   ├── DadosAdicionais.java        ← interface
│   ├── DadosFisicos.java
│   ├── DadosDigitais.java
│   ├── DadosPerecivel.java
│   ├── EventoEstoque.java
│   └── enums/
│       ├── CategoriaEstoque.java
│       └── TipoEvento.java
│
├── dto/
│   ├── CadastrarProdutoRequest.java
│   ├── MovimentacaoRequest.java
│   ├── ProdutoResponse.java
│   └── RelatorioEstoqueResponse.java
│
├── repository/
│   ├── ProdutoRepository.java      ← interface
│   └── ProdutoRepositoryEmMemoria.java
│
├── service/
│   ├── EstoqueService.java
│   └── PrecificacaoService.java
│
├── pricing/
│   ├── Precificavel.java           ← interface (Strategy)
│   ├── PrecoNormal.java
│   ├── PrecoComDesconto.java
│   ├── PrecoComMarkup.java
│   └── PrecoDinamico.java
│
└── listener/
    ├── EventoListener.java         ← interface (Observer)
    ├── LogListener.java
    ├── AlertaReposicaoListener.java
    └── RelatorioEventosListener.java
```

> 💡 **Por que isso importa no Spring?**
> O Spring organiza o código exatamente assim. `@Repository`, `@Service`, e `@Component` são anotações que marcam classes nessas camadas. Aqui você faz o mesmo — sem anotação, mas com a mesma responsabilidade.

---

# 🏗️ DOMÍNIO

---

## 📌 Produto\<T extends DadosAdicionais\>

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
private final String dimensoesCm;
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

Cada uma implementa `resumo()` retornando uma string descritiva.

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

> 💡 **Equivalente Spring:** `ApplicationEvent`. No Spring, eventos são publicados via `ApplicationEventPublisher` e consumidos por `@EventListener`. Aqui você simula esse mecanismo manualmente.

---

# 📊 ENUMS

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

Mínimos sugeridos: ELETRONICO → 5, ALIMENTICIO → 20, VESTUARIO → 10, DIGITAL → 0, PERECIVEL → 15.

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

# 📦 DTOs

> 💡 **Por que DTOs?**
> No Spring, nunca se expõe a entidade de domínio diretamente na API. O DTO é o contrato público — o domínio é protegido. Aqui você pratica essa separação.

---

## CadastrarProdutoRequest

```java
private final String nome;
private final CategoriaEstoque categoria;
private final double precoBase;
private final int quantidadeInicial;
private final DadosAdicionais dadosAdicionais;
```

> Entrada para cadastro. O `id` é gerado internamente pelo serviço, não vem do request.

---

## MovimentacaoRequest

```java
private final String idProduto;
private final int quantidade;
private final TipoMovimentacao tipo; // ENTRADA ou SAIDA
```

---

## ProdutoResponse

```java
private final String id;
private final String nome;
private final CategoriaEstoque categoria;
private final double precoBase;
private final double precoFinal;    // já com estratégia aplicada
private final int quantidadeEmEstoque;
private final String resumoDadosAdicionais;
private final boolean abaixoDoMinimo;
```

> Saída após qualquer operação. Nunca expõe o `Produto` diretamente.

---

## RelatorioEstoqueResponse

```java
private final int totalProdutos;
private final int produtosAbaixoDoMinimo;
private final int produtosSemEstoque;
private final double valorTotalEmEstoque;
private final Map<CategoriaEstoque, Integer> quantidadePorCategoria;
private final List<String> alertasValidade;
```

Imutável. **Builder obrigatório.**

> 💡 **Equivalente Spring:** Classes anotadas com `@Builder` do Lombok, ou ResponseEntity com body. Aqui você implementa o Builder manualmente.

---

# 🗄️ REPOSITORY

> 💡 **Equivalente Spring:** `@Repository` + `JpaRepository<T, ID>`. O Spring injeta automaticamente a implementação. Aqui você faz a injeção via construtor manualmente.

---

## Interface ProdutoRepository

```java
public interface ProdutoRepository {
    void salvar(Produto<?> produto);
    Optional<Produto<?>> buscarPorId(String id);
    List<Produto<?>> buscarTodos();
    boolean existePorId(String id);
    void atualizar(Produto<?> produto);
}
```

---

## ProdutoRepositoryEmMemoria

Implementa `ProdutoRepository` usando um `Map<String, Produto<?>>` internamente.

Nenhuma outra classe deve conhecer essa implementação — apenas a interface.

---

# ⚙️ SERVICES

> 💡 **Equivalente Spring:** Classes anotadas com `@Service`. Recebem dependências via `@Autowired` no construtor. Aqui você injeta via construtor manualmente — é exatamente o que o Spring faz por baixo.

---

## PrecificacaoService

Responsável exclusivamente por gerenciar e aplicar estratégias de precificação.

```java
// Injeção via construtor
public PrecificacaoService(Precificavel estrategiaInicial) { ... }
```

```java
public void alterarEstrategia(Precificavel novaEstrategia);
public double calcularPreco(double precoBase);
public String descricaoEstrategiaAtual();
```

---

## EstoqueService

Classe central. Recebe todas as dependências via construtor.

```java
// Injeção via construtor — simula @Autowired do Spring
public EstoqueService(
    ProdutoRepository repository,
    PrecificacaoService precificacaoService,
    List<EventoListener> listeners
) { ... }
```

```java
public ProdutoResponse cadastrarProduto(CadastrarProdutoRequest request);
public ProdutoResponse movimentarEstoque(MovimentacaoRequest request);
public ProdutoResponse consultarProduto(String idProduto);
public RelatorioEstoqueResponse gerarRelatorio();
```

> Todas as operações retornam DTOs, nunca entidades de domínio.

---

# 🔔 LISTENERS (Observer)

> 💡 **Equivalente Spring:** Métodos anotados com `@EventListener` ou classes que implementam `ApplicationListener<E>`. São registrados no contexto e chamados automaticamente quando um evento é publicado.

---

## Interface EventoListener

```java
public interface EventoListener {
    void aoReceberEvento(EventoEstoque evento);
}
```

---

## LogListener

Imprime no console cada evento recebido:

```
[2025-06-01 14:32] ESTOQUE_ZERADO — Produto: Notebook Gamer (ID: P001) | Anterior: 1 | Atual: 0
```

---

## AlertaReposicaoListener

Age apenas em `ESTOQUE_MINIMO_ATINGIDO` e `ESTOQUE_ZERADO`. Acumula alertas internamente.

```java
public List<String> getAlertas(); // imutável
```

---

## RelatorioEventosListener

Acumula todos os eventos recebidos.

```java
public List<EventoEstoque> getEventos(); // imutável
public long contarEventosPorTipo(TipoEvento tipo);
```

---

# 💲 ESTRATÉGIAS DE PRECIFICAÇÃO (Strategy)

> 💡 **Equivalente Spring:** Beans de Strategy injetados condicionalmente com `@Qualifier` ou `@ConditionalOnProperty`. Aqui você troca a estratégia via `alterarEstrategia()`.

---

## Interface Precificavel

```java
public interface Precificavel {
    double calcularPrecoFinal(double precoBase);
    String descricaoEstrategia();
}
```

Implementações obrigatórias:

**PrecoNormal** — retorna `precoBase` sem alteração.

**PrecoComDesconto**
```java
private final double percentualDesconto; // 0.0 a 1.0
// precoFinal = precoBase * (1 - percentualDesconto)
```

**PrecoComMarkup**
```java
private final double percentualMarkup;
// precoFinal = precoBase * (1 + percentualMarkup)
```

**PrecoDinamico**
```java
private final int estoqueAlvo;
private final ProdutoRepository repository; // injetado via construtor
// estoque <= alvo → precoBase * 1.30
// estoque > alvo  → precoBase * 0.90
```

> `PrecoDinamico` recebe o `ProdutoRepository` via construtor para consultar o estoque — sem quebrar encapsulamento.

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Cadastro de Produto

1. Gerar `id` único internamente (ex: UUID ou sequencial)
2. Criar `Produto` a partir do `CadastrarProdutoRequest`
3. Salvar via `repository.salvar()`
4. Publicar evento `PRODUTO_CADASTRADO`
5. Retornar `ProdutoResponse` montado a partir do domínio

---

## 🔹 Regra 2 — Movimentação de Estoque

**Entrada:**
1. Validar `quantidade > 0`
2. Buscar produto — não encontrado → `NoSuchElementException`
3. Atualizar quantidade
4. Publicar `ENTRADA_ESTOQUE`

**Saída:**
1. Validar `quantidade > 0`
2. Buscar produto — não encontrado → `NoSuchElementException`
3. Validar estoque suficiente — insuficiente → `IllegalStateException`
4. Atualizar quantidade
5. Publicar `SAIDA_ESTOQUE`
6. Se zerou → publicar também `ESTOQUE_ZERADO`
7. Se ficou abaixo do mínimo → publicar também `ESTOQUE_MINIMO_ATINGIDO`

---

## 🔹 Regra 3 — Publicação de Eventos

O `EstoqueService` mantém a lista de `EventoListener` recebida no construtor.

A cada evento gerado, notifica **todos os listeners na ordem em que foram registrados**.

---

## 🔹 Regra 4 — Consulta de Preço

`consultarProduto()` usa `PrecificacaoService.calcularPreco()` para preencher `precoFinal` no `ProdutoResponse`.

---

## 🔹 Regra 5 — Alerta de Validade no Relatório

Ao gerar `RelatorioEstoqueResponse`, verificar produtos com `DadosPerecivel`. Se `dataValidade` estiver nos próximos **7 dias** → incluir em `alertasValidade` e publicar `VALIDADE_PROXIMA`.

---

## 🔹 Regra 6 — Valor Total em Estoque

```
valorTotal = Σ (precoBase * quantidadeEmEstoque)
```

Usar `precoBase`, não o preço com estratégia.

---

# 🚫 Restrições

❌ Nenhuma dependência criada com `new` dentro de `Service` — somente via construtor
❌ Nenhuma entidade de domínio exposta fora da camada de serviço
❌ Não usar `instanceof` para desviar lógica — use polimorfismo
❌ `RelatorioEstoqueResponse` somente via Builder
❌ `EstoqueService` não conhece implementações concretas de `Repository` nem de `Listener`
❌ Nenhuma regra de negócio no `Main`

---

# 📌 Exemplo de Main (montagem das dependências)

```java
public class Main {
    public static void main(String[] args) {

        // Repository (como um Bean Spring)
        ProdutoRepository repository = new ProdutoRepositoryEmMemoria();

        // Listeners (como @EventListener)
        LogListener log = new LogListener();
        AlertaReposicaoListener alerta = new AlertaReposicaoListener();
        RelatorioEventosListener relatorioEventos = new RelatorioEventosListener();

        // Estratégia inicial (como um @Bean de Strategy)
        Precificavel estrategia = new PrecoNormal();
        PrecificacaoService precificacao = new PrecificacaoService(estrategia);

        // Service com injeção via construtor (como @Autowired)
        EstoqueService service = new EstoqueService(
            repository,
            precificacao,
            List.of(log, alerta, relatorioEventos)
        );

        // Uso
        service.cadastrarProduto(...);
        service.movimentarEstoque(...);
        service.gerarRelatorio();
    }
}
```

---

# 📌 Exemplo de Execução

```
Cadastro: Notebook Gamer | ELETRONICO | R$3.000 | Qtd: 10
  → [LOG] PRODUTO_CADASTRADO — Notebook Gamer

Saída: Notebook Gamer x6
  → [LOG] SAIDA_ESTOQUE (10 → 4)
  → [LOG] ESTOQUE_MINIMO_ATINGIDO (mínimo ELETRONICO = 5)
  → [ALERTA] Repor Notebook Gamer — abaixo do mínimo recomendado

Estratégia alterada: PrecoComDesconto(15%)
Consulta Notebook: precoBase R$3.000 → precoFinal R$2.550

--- Relatório ---
Total: 3 produtos
Abaixo do mínimo: 1
Sem estoque: 0
Valor total: R$31.985,00
Alertas de validade: ["Iogurte Natural vence em 3 dias — Lote: L2024A"]
```

---

# 🧠 O que este desafio testa

| Conceito Java | Equivalente Spring praticado |
|---|---|
| Interface + Polimorfismo | `@Repository`, `@Service` com contrato via interface |
| Injeção via construtor | `@Autowired` no construtor |
| Observer / Listener | `@EventListener` / `ApplicationEvent` |
| Strategy | `@Bean` com `@Qualifier` |
| Generics | Repositórios genéricos do Spring Data |
| DTOs | Request/Response bodies da API REST |
| Builder | Construção de respostas complexas |
| Separação de camadas | Arquitetura padrão Spring Boot |

---

# 📊 Nível de Complexidade

| Desafio | Foco |
|---------|------|
| 19 | Motor antifraude + enums com comportamento |
| **20** | **Arquitetura Spring-style + Generics + Strategy + Observer** |
| 21 | A definir 🚀 |