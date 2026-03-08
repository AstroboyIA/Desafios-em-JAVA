package desaio20.src.domain;

import java.time.LocalDateTime;

import desaio20.src.domain.enums.TipoEvento;

public class EventoEstoque {

    private final TipoEvento tipo;
    private final String idProduto;
    private final String nomeProduto;
    private final int quantidadeAnterior;
    private final int quantidadeAtual;
    private final LocalDateTime momento;

    public EventoEstoque(TipoEvento tipo, String idProduto, String nomeProduto, int quantidadeAnterior,
            int quantidadeAtual, LocalDateTime momento) {
        this.tipo = tipo;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidadeAnterior = quantidadeAnterior;
        this.quantidadeAtual = quantidadeAtual;
        this.momento = momento;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidadeAnterior() {
        return quantidadeAnterior;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

}
