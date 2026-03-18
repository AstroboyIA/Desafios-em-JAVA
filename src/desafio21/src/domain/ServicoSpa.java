package desafio21.src.domain;

import java.time.LocalDateTime;
import java.util.List;

public class ServicoSpa implements ServicoAdicional {

    private final double valor;
    private final String tipoDeTratamento;
    private final LocalDateTime horarioAgendado;

    public ServicoSpa(double valor, String tipoDeTratamento, LocalDateTime horarioAgendado) {
        this.valor = valor;
        this.tipoDeTratamento = tipoDeTratamento;
        this.horarioAgendado = horarioAgendado;
    }
    
    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public String descricao() {
        return
        "Valor: " + valor +
        "Tratamento: " + tipoDeTratamento +
        "Hórario agendado: " + horarioAgendado;
    }

    @Override
    public void aplicarAlertas(Reserva<?> reserva, List<String> alertas) {}
}
