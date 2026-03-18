package desafio21.src.domain;

import java.time.LocalDateTime;
import java.util.List;

public class ServicoTransfer implements ServicoAdicional {
    
    private final double valor;
    private final String destino;
    private final LocalDateTime horarioPartida;

    public ServicoTransfer(double valor, String destino, LocalDateTime horarioPartida) {
        this.valor = valor;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
    }

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public String descricao() {
        return
        "Valor: " + valor +
        "Destino: " + destino +
        "Horario de partida: " + horarioPartida;
    }

    @Override
    public void aplicarAlertas(Reserva<?> reserva, List<String> alertas) {}
}
