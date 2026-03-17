package desafio21.src.service;

import java.util.List;

import desafio21.src.dto.CriarReservaRequest;
import desafio21.src.dto.RelatorioOcupacaoResponse;
import desafio21.src.dto.ReservaResponse;
import desafio21.src.repository.QuartoRepository;

public class ReservaService {
    
    //public ReservaService(QuartoRepository quartoRespository, ReservaRepository reservaRepository, TarifacaoService tarifacaoService, List<ReservaListener> listeners) {}
    
    /*public ReservaResonse criarReserva (CriarReservaRequest request) {}
    1. Validar que quarto existe → `NoSuchElementException`
    2. Validar que quarto está disponível → `IllegalStateException`
    3. Validar que `dataCheckout` é após `dataCheckin` → `IllegalArgumentException`
    4. Gerar `id` via UUID
    5. Criar reserva com status `CONFIRMADA`
    6. Marcar quarto como indisponível
    7. Publicar `RESERVA_CRIADA`
    8. Retornar `ReservaResponse`
    */

    /*public ReservaResponse realizarCheckin (CheckinRequest request) {
    1. Buscar reserva → `NoSuchElementException`
    2. Validar `status.permiteCheckin()` → `IllegalStateException`
    3. Alterar status para `CHECKIN_REALIZADO`
    4. Publicar `CHECKIN_REALIZADO`
    5. Retornar `ReservaResponse`
    }*/

    /*public ReservaResponse realizarCheckout (CheckoutRequest request) {
    1. Buscar reserva → `NoSuchElementException`
    2. Validar `status.permiteCheckout()` → `IllegalStateException`
    3. Alterar status para `CHECKOUT_REALIZADO`
    4. Liberar quarto (disponível = true)
    5. Publicar `CHECKOUT_REALIZADO`
    6. Retornar `ReservaResponse`
    }*/

    /*public ReservaResponse cancelarReserva (String idReserva) {
    
    1. Buscar reserva → `NoSuchElementException`
    2. Validar `status.permiteCancelamento()` → `IllegalStateException`
    3. Alterar status para `CANCELADA`
    4. Liberar quarto
    5. Publicar `RESERVA_CANCELADA`
    6. Retornar `ReservaResponse`
    
    }*/

    //public ReservaResponse consultarReserva (String idReserva) {}

    //public List<ReservaResponse> listarReservas () {}

    //public RelatorioOcupacaoResponse gerarRelatorio () {}
    
}
