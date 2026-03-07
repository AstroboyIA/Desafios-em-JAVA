package desaio20.src.domain;

import java.time.LocalDate;

public class DadosPerecivel implements DadosAdicionais {
    
    private final LocalDate dataValidade;
    private final String loteRastreamento;
    
    public DadosPerecivel(LocalDate dataValidade, String loteRastreamento) {
        this.dataValidade = dataValidade;
        this.loteRastreamento = loteRastreamento;
    }

    public String resumo() {
        return
        "Data de válidade: " + dataValidade +
        "Lote de restramento: " + loteRastreamento;
    }
}
