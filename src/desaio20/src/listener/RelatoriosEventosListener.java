package desaio20.src.listener;

import java.util.ArrayList;
import java.util.List;

import desaio20.src.domain.EventoEstoque;
import desaio20.src.domain.enums.TipoEvento;

public class RelatoriosEventosListener implements EventoListener{
    
    private final List<EventoEstoque> eventos = new ArrayList<>();
    
    public List<EventoEstoque> getEventos() {
        return List.copyOf(eventos);
    }

    public long contarEventosPorTipo(TipoEvento tipo) {
        return eventos.stream().filter();
    }
}
