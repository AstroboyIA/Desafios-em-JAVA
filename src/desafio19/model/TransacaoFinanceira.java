package desafio19.model;

import desafio19.model.enums.CanalTransacao;
import desafio19.model.enums.CategoriaTransacao;
import desafio19.model.enums.CriticidadeConta;
import desafio19.model.enums.NivelFraude;
import desafio19.model.enums.PerfilRiscoCliente;
import desafio19.model.enums.StatusAutorizacao;

public class TransacaoFinanceira {

    private final String id;
    private final double valor;
    private final CategoriaTransacao categoria;
    private final CanalTransacao canal;
    private final int minutoDia;
    private final ContaCartao conta;

    public TransacaoFinanceira(String id, double valor, CategoriaTransacao categoria, CanalTransacao canal,
            int minutoDia, ContaCartao conta) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID da transação não pode ser nulo.");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo.");
        }

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria da transação não pode ser nula.");
        }

        if (canal == null) {
            throw new IllegalArgumentException("Canal da transação não pode ser nulo.");
        }

        if (minutoDia < 0 || minutoDia >= 1440) {
            throw new IllegalArgumentException("Minuto do dia deve estar entre 0 e 1440.");
        }

        if (conta == null) {
            throw new IllegalArgumentException("Conta associada à transação não pode ser nula.");
        }

        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.canal = canal;
        this.minutoDia = minutoDia;
        this.conta = conta;
    }

    public String getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public CategoriaTransacao getCategoria() {
        return categoria;
    }

    public CanalTransacao getCanal() {
        return canal;
    }

    public int getMinutoDia() {
        return minutoDia;
    }

    public ContaCartao getConta() {
        return conta;
    }

    public int calcularScoreAntifraude() {

        int scoreAntifraude = 0;

        int score = (int) (valor / 100);
        int riscoCategoria = categoria.getPesoRisco();
        int riscoCanal = canal.getPesoRiscoCanal();
        int multiplicador = conta.getPerfilRisco().getMultiplicadorRisco();

        scoreAntifraude = score + riscoCategoria + riscoCanal;
        scoreAntifraude *= multiplicador;

        if (minutoDia >= 0 && minutoDia <= 300) {
            scoreAntifraude += 5;
        }

        if (scoreAntifraude < 0) {
            scoreAntifraude = 0;
        } else if (scoreAntifraude > 100) {
            scoreAntifraude = 100;
        }

        return scoreAntifraude;
    }

    public NivelFraude classificarFraude() {

        int score = calcularScoreAntifraude();

        if (score < 20) {
            return NivelFraude.NORMAL;
        } else if (score < 40) {
            return NivelFraude.SUSPEITA;
        } else if (score < 70) {
            return NivelFraude.ALTO_RISCO;
        } else {
            return NivelFraude.FRAUDE_CONFIRMADA;
        }
    }

    public double calcularIndiceExposicao() {

        double indice;
        double limite = conta.getLimite();

        indice = conta.getSaldoUtilizado() / limite * 100;

        if (indice > 100) {
            indice = 100;
        } else if (indice < 0) {
            indice = 0;
        }

        return indice;
    }

    public CriticidadeConta classificarCriticidade() {

        CriticidadeConta criticidade;

        if (conta.isBloqueado()) {
            criticidade = CriticidadeConta.BLOQUEADA;
        } else if (classificarFraude() == NivelFraude.FRAUDE_CONFIRMADA || calcularIndiceExposicao() >= 90) {
            criticidade = CriticidadeConta.CRITICA;
        } else if (conta.getTentativasSuspeitas() >= 2) {
            criticidade = CriticidadeConta.MONITORADA;
        } else {
            criticidade = CriticidadeConta.ESTAVEL;
        }

        return criticidade;
    }
}
