package desafio19.model;

import java.util.ArrayList;
import java.util.List;

import desafio19.model.enums.PerfilRiscoCliente;

public class ContaCartao {

    private final String numero;
    private final PerfilRiscoCliente perfilRisco;
    private final double limite;
    private double saldoUtilizado;
    private boolean bloqueado;
    private int tentativasSuspeitas;

    public ContaCartao(String numero, PerfilRiscoCliente perfilRisco, double limite, double saldoUtilizado,
            boolean bloqueado, int tentativasSuspeitas) {

        if (numero == null) {
            throw new IllegalArgumentException("O número precisa ser informado!");
        }

        if (limite <= 0) {
            throw new IllegalArgumentException("O limite precisa ser positivo!");
        }

        if (saldoUtilizado < 0) {
            throw new IllegalArgumentException("O saldo utilizado não pode ser negativo!");
        }

        if (saldoUtilizado > limite) {
            throw new IllegalArgumentException("O saldo utilizado não pode ser maior que o limite da conta!");
        }

        this.numero = numero;
        this.perfilRisco = perfilRisco;
        this.limite = limite;
        this.saldoUtilizado = saldoUtilizado;
        this.bloqueado = bloqueado;
        this.tentativasSuspeitas = tentativasSuspeitas;
    }

    public void setTentativasSuspeitas(int tentativasSuspeitas) {
        this.tentativasSuspeitas = tentativasSuspeitas;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
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

    public int getTentativasSuspeitas() {
        return tentativasSuspeitas;
    }

    private final List<AnaliseAntifraude> historicoAnalises = new ArrayList<>();

    public void adicionarAnalise(AnaliseAntifraude analise) {

        historicoAnalises.add(analise);

    }

}
