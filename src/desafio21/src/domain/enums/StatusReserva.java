package desafio21.src.domain.enums;

public enum StatusReserva {

    CONFIRMADA,
    CHECKIN_REALIZADO,
    CHECKOUT_REALIZADO,
    CANCELADA;

    public boolean permiteCheckin;
    public boolean permiteCheckout;
    public boolean permiteCancelamento;

}