package desaio20.src.domain;

import java.util.List;
import java.util.function.Consumer;

public interface DadosAdicionais {

    String resumo();

    void verificarAlertas(Produto<?> produto, List<String> alertas, Consumer<EventoEstoque> publicador);

}