package desafio19.model;

import java.util.ArrayList;
import java.util.List;

import desafio19.model.enums.NivelFraude;
import desafio19.model.enums.PerfilRiscoCliente;
import desafio19.model.enums.StatusAutorizacao;

public class ContaCartao {

    private final String numero;
    private final PerfilRiscoCliente perfilRisco;
    private final double limite;
    private double saldoUtilizado;
    private boolean bloqueado;
    private int tentativasSuspeitas;

    public ContaCartao(String numero, PerfilRiscoCliente perfilRisco, double limite) {

        if (numero == null) {
            throw new IllegalArgumentException("O número precisa ser informado!");
        }

        if (limite <= 0) {
            throw new IllegalArgumentException("O limite precisa ser positivo!");
        }

        if (perfilRisco == null) {
            throw new IllegalArgumentException("O perfil de risco precisa ser informado!");
        }

        this.numero = numero;
        this.perfilRisco = perfilRisco;
        this.limite = limite;

        this.saldoUtilizado = 0;
        this.bloqueado = false;
        this.tentativasSuspeitas = 0;
    }

    public String getNumero() {
        return numero;
    }

    public double getLimite() {
        return limite;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    private void bloquear(){
        this.bloqueado = true;
    }

    public int getTentativasSuspeitas() {
        return tentativasSuspeitas;
    }

    public PerfilRiscoCliente getPerfilRisco() {
        return perfilRisco;
    }

    private final List<AnaliseAntifraude> historicoAnalises = new ArrayList<>();

    public void adicionarAnalise(AnaliseAntifraude analise) {

        historicoAnalises.add(analise);

    }

    public void regitrarTentativas() {

        tentativasSuspeitas ++;

        if (tentativasSuspeitas >= 3) {
            bloquear();
        }
    }

    public void bloquearFraude() {
        bloquear();
    }

    public StatusAutorizacao autorizarTransacao(TransacaoFinanceira transacao, ContaCartao conta) {

        if (conta.isBloqueado()) {
            return StatusAutorizacao.BLOQUEADA;
        }

        int score = transacao.calcularScoreAntifraude();
        NivelFraude nivel = transacao.classificarFraude();
        StatusAutorizacao status;
        double novoSaldoUtilizado = conta.getSaldoUtilizado() + transacao.getValor();

        if (novoSaldoUtilizado > conta.getLimite()) {
            status = StatusAutorizacao.NEGADA;

        } else if (nivel == NivelFraude.FRAUDE_CONFIRMADA) {
            status = StatusAutorizacao.BLOQUEADA;
            conta.bloquearFraude();

        } else if (nivel == NivelFraude.ALTO_RISCO) {
            status = StatusAutorizacao.NEGADA;
            conta.regitrarTentativas();

        } else if (nivel == NivelFraude.SUSPEITA) {
            status = StatusAutorizacao.APROVADA;
            conta.regitrarTentativas();

        } else {
            status = StatusAutorizacao.APROVADA;
        }

        if (conta.isBloqueado()) {
            status = StatusAutorizacao.BLOQUEADA;
        }

        if (status == StatusAutorizacao.APROVADA) {
            conta.saldoUtilizado = novoSaldoUtilizado;
        }

        AnaliseAntifraude analise = new AnaliseAntifraude(score, nivel, status, transacao.getValor());

        conta.adicionarAnalise(analise);

        return status;
    }
}
