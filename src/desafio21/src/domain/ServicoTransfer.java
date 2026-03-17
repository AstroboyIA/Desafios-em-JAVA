package desafio21.src.domain;

import java.time.LocalDateTime;

public class ServicoTransfer {
    
    private final double valor;
    private final String destino;
    private final LocalDateTime horarioPartida;
    
    public double getValor() {
        return valor;
    }


    public String getDestino() {
        return destino;
    }


    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    public ServicoTransfer(double valor, String destino, LocalDateTime horarioPartida) {
        this.valor = valor;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
    }
}
