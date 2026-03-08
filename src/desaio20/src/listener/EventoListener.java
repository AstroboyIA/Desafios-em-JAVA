package desaio20.src.listener;

import desaio20.src.domain.EventoEstoque;

public interface EventoListener {
    
    void aoReceberEvento(EventoEstoque evento);
    
}
