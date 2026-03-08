package desaio20.src.listener;

import desaio20.src.domain.EventoEstoque;

public class LogListener implements EventoListener {

    @Override
    public void aoReceberEvento(EventoEstoque evento) {
        System.out.printf("[%s] %s - Produto: %s | Anterior: %s | Atual: %d%n",
                evento.getMomento(),
                evento.getTipo().getDescricao(),
                evento.getNomeProduto(),
                evento.getQuantidadeAnterior(),
                evento.getQuantidadeAtual());
    }
}