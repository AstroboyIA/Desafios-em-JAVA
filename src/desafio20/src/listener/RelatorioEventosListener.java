package desafio20.src.listener;

import java.util.ArrayList;
import java.util.List;

import desafio20.src.domain.EventoEstoque;
import desafio20.src.domain.enums.TipoEvento;

public class RelatorioEventosListener implements EventoListener {

    private final List<EventoEstoque> eventos = new ArrayList<>();


    @Override
    public void aoReceberEvento(EventoEstoque evento) {
        eventos.add(evento);
    }

    public List<EventoEstoque> getEventos() {
        return List.copyOf(eventos);
    }

    public long contarEventosPorTipo(TipoEvento tipo) {
        return eventos.stream()
                .filter(e -> e.getTipo() == tipo)
                .count();
    }
}
