package desaio20.src.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

import desaio20.src.domain.enums.TipoEvento;

public class DadosPerecivel implements DadosAdicionais {
    
    private final LocalDate dataValidade;
    private final String loteRastreamento;
    
    public DadosPerecivel(LocalDate dataValidade, String loteRastreamento) {
        this.dataValidade = dataValidade;
        this.loteRastreamento = loteRastreamento;
    }

    @Override
    public String resumo() {
        return
        "Data de válidade: " + dataValidade +
        " | Lote de restreamento: " + loteRastreamento;
    }

    @Override
    public void verificarAlertas(Produto<?> produto, List<String> alertas, Consumer<EventoEstoque> publicador) {

        // verifica nos proximos 7 dias
        LocalDate hoje = LocalDate.now();
        LocalDate limiteAlerta = hoje.plusDays(7);

        if (dataValidade.isAfter(hoje) && !dataValidade.isAfter(limiteAlerta)) {
            alertas.add(String.format(
                "%s vence em %s - Lote %s",
                produto.getNome(),
                dataValidade,
                loteRastreamento
            ));

            publicador.accept(new EventoEstoque(
                TipoEvento.VALIDADE_PROXIMA,
                produto.getId(),
                produto.getNome(),
                produto.getQuantidadeEmEstoque(),
                produto.getQuantidadeEmEstoque(),
                LocalDateTime.now()
            ));
        }
    }
}