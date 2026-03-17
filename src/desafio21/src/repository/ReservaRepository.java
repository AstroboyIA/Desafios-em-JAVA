package desafio21.src.repository;

import java.util.List;
import java.util.Optional;

import desafio21.src.domain.Reserva;
import desafio21.src.domain.enums.StatusReserva;

public interface ReservaRepository {
    
    void salvar (Reserva<?> reserva);
    Optional<Reserva<?>> buscarPorId (String id);
    List<Reserva<?>> buscarTodas();
    List<Reserva<?>> buscarPorStatus (StatusReserva status);
    void atualizar (Reserva<?> reserva);

}
