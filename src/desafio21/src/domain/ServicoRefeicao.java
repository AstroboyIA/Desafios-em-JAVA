package desafio21.src.domain;

import java.util.List;

public class ServicoRefeicao implements ServicoAdicional {
    
    private final double valor;
    private final String tipoDieta;
    private final boolean incluidoNoPacote;

    public ServicoRefeicao(double valor, String tipoDieta, boolean incluidoNoPacote) {
        this.valor = valor;
        this.tipoDieta = tipoDieta;
        this.incluidoNoPacote = incluidoNoPacote;
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public String descricao() {
        return
        "Valor: " + valor +
        "Dieta: " + tipoDieta +
        "Incluido no pacote? " + (incluidoNoPacote ? "SIM" : "NÃO");
    }

    @Override
    public void aplicarAlertas(Reserva<?> reserva, List<String> alertas) {}
}