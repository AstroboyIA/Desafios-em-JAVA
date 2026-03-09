package desaio20.src.listener;

import java.util.ArrayList;
import java.util.List;

import desaio20.src.domain.EventoEstoque;
import desaio20.src.domain.enums.TipoEvento;

public class AlertaReposicaoListener implements EventoListener {

    private final List<String> alertas = new ArrayList<>();

    @Override
    public void aoReceberEvento(EventoEstoque evento) {
        if (evento.getTipo() == TipoEvento.ESTOQUE_MINIMO_ATINGIDO || evento.getTipo() == TipoEvento.ESTOQUE_ZERADO) {

            alertas.add(String.format(
                    "Repor produto %s - %s",
                    evento.getNomeProduto(),
                    evento.getTipo().getDescricao()));
        }
    }

    public List<String> getAlertas() {
        return List.copyOf(alertas);
    }

}