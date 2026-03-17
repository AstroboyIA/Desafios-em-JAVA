package desafio21.src.domain;

import java.util.List;

public interface ServicoAdicional {
    
    double getvalor();
    String descricao();
    void aplicarAlertas(Reserva<?> reserva, List<String> alertas);

}
