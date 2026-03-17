package desafio21.src.dto;

import java.time.LocalDate;
import java.util.List;

import desafio21.src.domain.enums.StatusReserva;
import desafio21.src.domain.enums.TipoQuarto;

public class ReservaResponse {
    
    private final String id;
    private final String numeroQuarto;
    private final TipoQuarto tipoQuarto;
    private final String nomeHospede;
    private final LocalDate dataCheckin;
    private final LocalDate dataCheckout;
    private final int totalDiarias;
    private final double valorDiarias; // tarifaBase * diarias com estrategia aplicada
    private final double valorServicos; // soma dos servicos adicionais
    private final double valorTotal; // diarias + servicos
    private final StatusReserva status;
    private final List<String> alertasServicos;
    
    public ReservaResponse(String id, String numeroQuarto, TipoQuarto tipoQuarto, String nomeHospede,
            LocalDate dataCheckin, LocalDate dataCheckout, int totalDiarias, double valorDiarias, double valorServicos,
            double valorTotal, StatusReserva status, List<String> alertasServicos) {
        this.id = id;
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
        this.nomeHospede = nomeHospede;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.totalDiarias = totalDiarias;
        this.valorDiarias = valorDiarias;
        this.valorServicos = valorServicos;
        this.valorTotal = valorTotal;
        this.status = status;
        this.alertasServicos = alertasServicos;
    }

}
