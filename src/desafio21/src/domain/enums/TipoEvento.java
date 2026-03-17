package desafio21.src.domain.enums;

public enum TipoEvento {
    
    RESERVA_CRIADA("Reserva Criada."),
    CHECKIN_REALIZADO("Checkin Realizado."),
    CHECKOU_REALIZADO("Checkout Realizado."),
    RESERVA_CANCELADA("Reserva Criada."),
    QUARTO_INDISPONIVEL("Quarto Indisponível."),
    ALERTA_SERVICO("Alerta de Serviço.");

    public String getDescricao;

    public String getGetDescricao() {
        return getDescricao;
    }

    private TipoEvento(String getDescricao) {
        this.getDescricao = getDescricao;
    }
}
