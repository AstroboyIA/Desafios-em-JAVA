package desafio20.src.listener;

import desafio20.src.domain.EventoEstoque;

public interface EventoListener {
    
    void aoReceberEvento(EventoEstoque evento);
    
}
