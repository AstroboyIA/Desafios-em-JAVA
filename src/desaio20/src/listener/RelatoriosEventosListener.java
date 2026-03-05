package desaio20.src.listener;

import java.util.List;

import desaio20.src.domain.EventoEstoque;
import desaio20.src.domain.enums.TipoEvento;

public interface RelatoriosEventosListener {
    
    public List<EventoEstoque> getEventos();

    public long contarEventosPorTipo(TipoEvento tipo);
}
