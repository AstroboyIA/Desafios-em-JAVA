package desafio21.src.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import desafio21.src.domain.ServicoAdicional;

public class CriarReservaRequest {
    
    // String idGerado = UUID.randomUUID().toString(); deve ser gerado via service
    private final String numeroQuarto;
    private final String nomeHospede;
    private final LocalDate dataCheckin;
    private final LocalDate dataCheckout;
    private final List<ServicoAdicional> servicosAdicionais;

    public CriarReservaRequest(String numeroQuarto, String nomeHospede, LocalDate dataCheckin, LocalDate dataCheckout,
            List<ServicoAdicional> servicosAdicionais) {
        this.numeroQuarto = numeroQuarto;
        this.nomeHospede = nomeHospede;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.servicosAdicionais = servicosAdicionais;
    }

}
