# 🏨 DESAFIO 21 — Sistema de Reservas de Hotel (Arquitetura Spring-Style)

---

# 🎯 Objetivo

Construir um sistema de gestão hoteleira com **arquitetura em camadas**, consolidando os padrões do Spring Boot praticados no Desafio 20 em um contexto completamente novo:

* Camadas separadas: **Service**, **Repository**, **Domain**
* **DTOs** de entrada e saída desacoplados do domínio
* **Injeção de dependência via construtor**
* **Eventos e Listeners** desacoplados (Observer)
* **Generics**, **Strategy** e **Polimorfismo**

Nenhuma regra no `Main`. O `Main` apenas monta as dependências e chama o serviço.

---

# 🏗️ ESTRUTURA DE PACOTES

```
src/
├── domain/
│   ├── Quarto.java
│   ├── Reserva.java
│   ├── ServicoAdicional.java       ← interface
│   ├── ServicoSpa.java
│   ├── ServicoTransfer.java
│   ├── ServicoRefeicao.java
│   ├── EventoReserva.java
│   └── enums/
│       ├── TipoQuarto.java
│       ├── StatusReserva.java
│       └── TipoEvento.java
│
├── dto/
│   ├── CriarReservaRequest.java
│   ├── CheckinRequest.java
│   ├── CheckoutRequest.java
│   ├── ReservaResponse.java
│   └── RelatorioOcupacaoResponse.java
│
├── repository/
│   ├── QuartoRepository.java       ← interface
│   ├── QuartoRepositoryEmMemoria.java
│   ├── ReservaRepository.java      ← interface
│   └── ReservaRepositoryEmMemoria.java
│
├── service/
│   ├── ReservaService.java
│   └── TarifacaoService.java
│
├── pricing/
│   ├── TarifaCalculavel.java       ← interface (Strategy)
│   ├── TarifaPadrao.java
│   ├── TarifaTemporada.java
│   ├── TarifaDesconto.java
│   └── TarifaDinamica.java
│
└── listener/
    ├── ReservaListener.java        ← interface (Observer)
    ├── LogReservaListener.java
    ├── AlertaOcupacaoListener.java
    └── RelatorioReservasListener.java
```

---

# 🏗️ DOMÍNIO

---

## 📌 Quarto

```java
private final String numero;
private final TipoQuarto tipo;
private final double tarifaBase;
private final int capacidadeMaxima;
private boolean disponivel;
```

### Invariantes

1. `numero` não nulo ou vazio
2. `tipo` não nulo
3. `tarifaBase` > 0
4. `capacidadeMaxima` >= 1
5. Quarto começa sempre disponível

Violação → `IllegalArgumentException`

---

## 📌 Reserva\<T extends ServicoAdicional\>

```java
private final String id;
private final String numeroQuarto;
private final String nomeHospede;
private final LocalDate dataCheckin;
private final LocalDate dataCheckout;
private StatusReserva status;
private final List<T> servicosAdicionais;
```

### Invariantes

1. `id` não nulo ou vazio
2. `numeroQuarto` não nulo
3. `nomeHospede` não nulo ou vazio
4. `dataCheckout` deve ser após `dataCheckin`
5. `servicosAdicionais` não nulo — pode ser vazio, nunca nulo

Violação → `IllegalArgumentException`

### Comportamentos

```java
public int calcularDiarias();           // dias entre checkin e checkout
public void adicionarServico(T servico);
public List<T> getServicos();           // imutável externamente
```

---

## 📌 Interface ServicoAdicional

```java
public interface ServicoAdicional {
    double getValor();
    String descricao();
    void aplicarAlertas(Reserva<?> reserva, List<String> alertas);
}
```

Implementações obrigatórias:

### ServicoSpa
```java
private final double valor;
private final String tipoDeTratamento;
private final LocalDateTime horarioAgendado;
```

### ServicoTransfer
```java
private final double valor;
private final String destino;
private final LocalDateTime horarioPartida;
```

### ServicoRefeicao
```java
private final double valor;
private final String tipoDieta; // ex: "vegano", "padrão"
private final boolean incluidoNoPacote;
```

> `aplicarAlertas()` — cada serviço decide se gera alertas. Exemplo: `ServicoSpa` alerta se o horário agendado for após o checkout.

---

## 📌 EventoReserva

```java
private final TipoEvento tipo;
private final String idReserva;
private final String nomeHospede;
private final String numeroQuarto;
private final StatusReserva statusAnterior;
private final StatusReserva statusAtual;
private final LocalDateTime momento;
```

Imutável. Sem setters.

> 💡 **Equivalente Spring:** `ApplicationEvent` com dados do contexto da operação.

---

# 📊 ENUMS

---

## TipoQuarto

```java
public enum TipoQuarto {
    STANDARD,
    LUXO,
    SUITE,
    SUITE_PRESIDENCIAL;

    public int getCapacidadePadrao();
    public double getMultiplicadorTarifa();
}
```

Sugestões:
- STANDARD → capacidade 2, multiplicador 1.0
- LUXO → capacidade 2, multiplicador 1.5
- SUITE → capacidade 4, multiplicador 2.0
- SUITE_PRESIDENCIAL → capacidade 6, multiplicador 3.5

---

## StatusReserva

```java
public enum StatusReserva {
    CONFIRMADA,
    CHECKIN_REALIZADO,
    CHECKOUT_REALIZADO,
    CANCELADA;

    public boolean permiteCheckin();
    public boolean permiteCheckout();
    public boolean permiteCancelamento();
}
```

---

## TipoEvento

```java
public enum TipoEvento {
    RESERVA_CRIADA,
    CHECKIN_REALIZADO,
    CHECKOUT_REALIZADO,
    RESERVA_CANCELADA,
    QUARTO_INDISPONIVEL,
    ALERTA_SERVICO;

    public String getDescricao();
}
```

---

# 📦 DTOs

---

## CriarReservaRequest

```java
private final String numeroQuarto;
private final String nomeHospede;
private final LocalDate dataCheckin;
private final LocalDate dataCheckout;
private final List<ServicoAdicional> servicosAdicionais;
```

> O `id` da reserva é gerado internamente pelo serviço via UUID.

---

## CheckinRequest / CheckoutRequest

```java
private final String idReserva;
```

> Simples — apenas identifica qual reserva sofrerá a operação.

---

## ReservaResponse

```java
private final String id;
private final String numeroQuarto;
private final TipoQuarto tipoQuarto;
private final String nomeHospede;
private final LocalDate dataCheckin;
private final LocalDate dataCheckout;
private final int totalDiarias;
private final double valorDiarias;       // tarifaBase * diarias com estratégia
private final double valorServicos;      // soma dos serviços adicionais
private final double valorTotal;         // diarias + servicos
private final StatusReserva status;
private final List<String> alertasServicos;
```

---

## RelatorioOcupacaoResponse

```java
private final int totalQuartos;
private final int quartosOcupados;
private final int quartosDisponiveis;
private final double taxaOcupacao;           // % ocupados
private final double receitaTotal;           // soma de todas as reservas ativas
private final Map<TipoQuarto, Integer> reservasPorTipo;
private final List<String> alertasServicos;  // alertas de todos os serviços
```

Imutável. **Builder obrigatório.**

---

# 🗄️ REPOSITORIES

---

## QuartoRepository

```java
public interface QuartoRepository {
    void salvar(Quarto quarto);
    Optional<Quarto> buscarPorNumero(String numero);
    List<Quarto> buscarTodos();
    List<Quarto> buscarDisponiveis();
    void atualizar(Quarto quarto);
}
```

---

## ReservaRepository

```java
public interface ReservaRepository {
    void salvar(Reserva<?> reserva);
    Optional<Reserva<?>> buscarPorId(String id);
    List<Reserva<?>> buscarTodas();
    List<Reserva<?>> buscarPorStatus(StatusReserva status);
    void atualizar(Reserva<?> reserva);
}
```

---

# ⚙️ SERVICES

---

## TarifacaoService

```java
public TarifacaoService(TarifaCalculavel tarifaInicial) { ... }

public void alterarTarifa(TarifaCalculavel novaTarifa);
public double calcularTarifa(double tarifaBase, int diarias);
public String descricaoTarifaAtual();
```

---

## ReservaService

```java
public ReservaService(
    QuartoRepository quartoRepository,
    ReservaRepository reservaRepository,
    TarifacaoService tarifacaoService,
    List<ReservaListener> listeners
) { ... }

public ReservaResponse criarReserva(CriarReservaRequest request);
public ReservaResponse realizarCheckin(CheckinRequest request);
public ReservaResponse realizarCheckout(CheckoutRequest request);
public ReservaResponse cancelarReserva(String idReserva);
public ReservaResponse consultarReserva(String idReserva);
public List<ReservaResponse> listarReservas();
public RelatorioOcupacaoResponse gerarRelatorio();
```

---

# 🔔 LISTENERS

---

## Interface ReservaListener

```java
public interface ReservaListener {
    void aoReceberEvento(EventoReserva evento);
}
```

---

## LogReservaListener

Imprime no console cada evento:

```
[2025-06-01 14:32] CHECKIN_REALIZADO — Hóspede: João Silva | Quarto: 101 | Reserva: abc-123
```

---

## AlertaOcupacaoListener

Age em `CHECKOUT_REALIZADO` e `RESERVA_CANCELADA`. Registra quartos que ficaram disponíveis.

```java
public List<String> getAlertas(); // imutável
```

---

## RelatorioReservasListener

Acumula todos os eventos.

```java
public List<EventoReserva> getEventos(); // imutável
public long contarEventosPorTipo(TipoEvento tipo);
```

---

# 💲 ESTRATÉGIAS DE TARIFAÇÃO

---

## Interface TarifaCalculavel

```java
public interface TarifaCalculavel {
    double calcularValor(double tarifaBase, int diarias);
    String descricaoTarifa();
}
```

---

**TarifaPadrao** — `tarifaBase * diarias` sem alteração.

**TarifaTemporada**
```java
private final double percentualAcrescimo; // ex: 0.30 = +30%
// valor = tarifaBase * diarias * (1 + percentualAcrescimo)
```

**TarifaDesconto**
```java
private final double percentualDesconto; // 0.0 a 1.0
// valor = tarifaBase * diarias * (1 - percentualDesconto)
```

**TarifaDinamica**
```java
private final double taxaOcupacaoAlvo; // ex: 0.80 = 80%
private final QuartoRepository quartoRepository;
// ocupação >= alvo → +25% (alta demanda)
// ocupação < alvo  → -15% (baixa demanda)
```

> `TarifaDinamica` injeta `QuartoRepository` via construtor para consultar ocupação atual — mesmo padrão do `PrecoDinamico` do Desafio 20.

---

# 🧠 REGRAS DE NEGÓCIO

---

## 🔹 Regra 1 — Criar Reserva

1. Validar que quarto existe → `NoSuchElementException`
2. Validar que quarto está disponível → `IllegalStateException`
3. Validar que `dataCheckout` é após `dataCheckin` → `IllegalArgumentException`
4. Gerar `id` via UUID
5. Criar reserva com status `CONFIRMADA`
6. Marcar quarto como indisponível
7. Publicar `RESERVA_CRIADA`
8. Retornar `ReservaResponse`

---

## 🔹 Regra 2 — Realizar Checkin

1. Buscar reserva → `NoSuchElementException`
2. Validar `status.permiteCheckin()` → `IllegalStateException`
3. Alterar status para `CHECKIN_REALIZADO`
4. Publicar `CHECKIN_REALIZADO`
5. Retornar `ReservaResponse`

---

## 🔹 Regra 3 — Realizar Checkout

1. Buscar reserva → `NoSuchElementException`
2. Validar `status.permiteCheckout()` → `IllegalStateException`
3. Alterar status para `CHECKOUT_REALIZADO`
4. Liberar quarto (disponível = true)
5. Publicar `CHECKOUT_REALIZADO`
6. Retornar `ReservaResponse`

---

## 🔹 Regra 4 — Cancelar Reserva

1. Buscar reserva → `NoSuchElementException`
2. Validar `status.permiteCancelamento()` → `IllegalStateException`
3. Alterar status para `CANCELADA`
4. Liberar quarto
5. Publicar `RESERVA_CANCELADA`
6. Retornar `ReservaResponse`

---

## 🔹 Regra 5 — Cálculo do Valor Total

```
valorDiarias  = TarifacaoService.calcularTarifa(tarifaBase, diarias)
valorServicos = Σ servico.getValor()
valorTotal    = valorDiarias + valorServicos
```

---

## 🔹 Regra 6 — Alertas de Serviços no Relatório

Ao gerar `RelatorioOcupacaoResponse`, percorrer todas as reservas com status `CHECKIN_REALIZADO` e chamar `servico.aplicarAlertas()` em cada serviço — sem `instanceof`, usando polimorfismo.

---

# 🚫 Restrições

❌ Nenhuma dependência criada com `new` dentro de `Service`
❌ Nenhuma entidade de domínio exposta fora da camada de serviço
❌ Não usar `instanceof` para desviar lógica — use polimorfismo
❌ `RelatorioOcupacaoResponse` somente via Builder
❌ `ReservaService` não conhece implementações concretas de Repository nem Listener
❌ Nenhuma regra de negócio no `Main`

---

# 📌 Exemplo de Execução

```
Quarto 101: STANDARD | R$200/noite | Capacidade: 2
Quarto 201: SUITE    | R$600/noite | Capacidade: 4

Reserva criada: João Silva | Quarto 101 | 10/06 → 13/06
  → [LOG] RESERVA_CRIADA | Status: CONFIRMADA

Checkin realizado: João Silva
  → [LOG] CHECKIN_REALIZADO | Quarto 101

Tarifa alterada: TarifaTemporada(+30%)
Consulta reserva:
  Diárias: 3 | Tarifa: R$200 * 3 * 1.30 = R$780,00
  Serviços: Spa R$150 + Transfer R$80 = R$230,00
  Total: R$1.010,00

Checkout realizado: João Silva
  → [LOG] CHECKOUT_REALIZADO | Quarto 101 liberado

--- Relatório ---
Total quartos: 2 | Ocupados: 0 | Disponíveis: 2
Taxa de ocupação: 0%
Receita total: R$1.010,00
Reservas por tipo: {STANDARD=1, SUITE=0}
```

---

# 🧠 O que este desafio testa

| Conceito Java | Equivalente Spring praticado |
|---|---|
| Interface + Polimorfismo | `@Repository`, `@Service` com contrato via interface |
| Injeção via construtor | `@Autowired` no construtor |
| Observer / Listener | `@EventListener` / `ApplicationEvent` |
| Strategy | Troca de tarifa em runtime |
| Generics | `Reserva<T extends ServicoAdicional>` |
| DTOs | Request/Response desacoplados do domínio |
| Builder | `RelatorioOcupacaoResponse` |
| Dois Repositories | Composição de dependências mais complexa |
| Máquina de estados | `StatusReserva` com transições válidas |

---

# 📊 Nível de Complexidade

| Desafio | Foco |
|---------|------|
| 20 | Arquitetura Spring-style — primeira vez |
| **21** | **Arquitetura Spring-style — consolidação com dois repositórios e máquina de estados** |
| 22 | A definir 🚀 |

---

A principal novidade em relação ao Desafio 20 é o **segundo repositório** (`ReservaRepository` + `QuartoRepository`) e a **máquina de estados** no `StatusReserva` — onde cada status sabe quais transições são válidas. Isso força você a pensar em fluxo de negócio, não só em CRUD.