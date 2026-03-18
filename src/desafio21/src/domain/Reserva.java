package desafio21.src.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import desafio21.src.domain.enums.StatusReserva;

public class Reserva<T extends ServicoAdicional> {

    private final String id;
    private final String numeroQuarto;
    private final String nomeHospede;
    private final LocalDate dataCheckin;
    private final LocalDate dataCheckout;
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

    public Reserva(String id, String numeroQuarto, String nomeHospede, LocalDate dataCheckin, LocalDate dataCheckout,
            StatusReserva status,
            List<T> servicosAdicionais) {

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("A reserva precisa de um ID para validação.");
        }

        if (numeroQuarto == null) {
            throw new IllegalArgumentException("Informe o número do quarto!");
        }

        if (nomeHospede == null || nomeHospede.isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do hospede!");
        }

        if (!dataCheckout.isAfter(dataCheckin)) {
            throw new IllegalArgumentException("A data de checkout deve ser após a data de checkin!");
        }

        if (servicosAdicionais == null) {
            throw new IllegalArgumentException("Informe se vai haver serviços aficionais ou não.");
        }

        this.id = id;
        this.numeroQuarto = numeroQuarto;
        this.nomeHospede = nomeHospede;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.status = status;
        this.servicosAdicionais = servicosAdicionais;
    }

    public int calcularDiarias(){
        return (int) ChronoUnit.DAYS.between(dataCheckin, dataCheckout);
    }

    public void adicionarServico(T servico) {
    }

    public /* List<T> */void getServicos() {
    }
}
