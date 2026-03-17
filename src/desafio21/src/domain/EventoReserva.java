package desafio21.src.domain;

import java.time.LocalDateTime;

import desafio20.src.domain.enums.TipoEvento;
import desafio21.src.domain.enums.StatusReserva;

public class EventoReserva {
    
    private final TipoEvento tipo;
    private final String idReserva;
    private final String nomeHospede;
    private final String numeroQuarto;
    private final StatusReserva statusAnterior;
    private final StatusReserva statusAtual;
    private final LocalDateTime momento;
    
    public TipoEvento getTipo() {
        return tipo;
    }
    public String getIdReserva() {
        return idReserva;
    }
    public String getNomeHospede() {
        return nomeHospede;
    }
    public String getNumeroQuarto() {
        return numeroQuarto;
    }
    public StatusReserva getStatusAnterior() {
        return statusAnterior;
    }
    public StatusReserva getStatusAtual() {
        return statusAtual;
    }
    public LocalDateTime getMomento() {
        return momento;
    }

    public EventoReserva(TipoEvento tipo, String idReserva, String nomeHospede, String numeroQuarto,
            StatusReserva statusAnterior, StatusReserva statusAtual, LocalDateTime momento) {
        this.tipo = tipo;
        this.idReserva = idReserva;
        this.nomeHospede = nomeHospede;
        this.numeroQuarto = numeroQuarto;
        this.statusAnterior = statusAnterior;
        this.statusAtual = statusAtual;
        this.momento = momento;
    }

}
