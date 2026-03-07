package desaio20.src.dto;

import desaio20.src.domain.enums.TipoEvento;

public class MovimentacaoRequest {
    
    private final String idProduto;
    private final int quantidade;
    private final TipoEvento tipo;
    
    public MovimentacaoRequest(String idProduto, int quantidade, TipoEvento tipo) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }
}
