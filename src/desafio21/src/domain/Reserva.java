package desafio21.src.domain;

import java.time.LocalDate;
import java.util.List;

import desafio21.src.domain.enums.StatusReserva;

public class Reserva <T extends ServicoAdicional> {

    private final String id;
    private final String numeroQuarto;
    private final String nomeHospede;
    private final LocalDate dataCheckin;
    private StatusReserva status;
    private final List<T> servicosAdicionais;
    

    public String getId() {
        return id;
    }

    public String getNumeroQuarto() {
        return numeroQuarto;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public List<T> getServicosAdicionais() {
        return servicosAdicionais;
    }

    public Reserva(String id, String numeroQuarto, String nomeHospede, LocalDate dataCheckin, StatusReserva status,
            List<T> servicosAdicionais) {
        this.id = id;
        this.numeroQuarto = numeroQuarto;
        this.nomeHospede = nomeHospede;
        this.dataCheckin = dataCheckin;
        this.status = status;
        this.servicosAdicionais = servicosAdicionais;
    }

    public /*int*/void calcularDiarias()
    {}

    public void adicionarServico(T servico)
    {}

    public /*List<T>*/void getServicos()
    {}
}
