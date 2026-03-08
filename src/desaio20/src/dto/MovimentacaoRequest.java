package desaio20.src.dto;

import desaio20.src.domain.enums.TipoEvento;
import desaio20.src.domain.enums.TipoMovimentacao;

public class MovimentacaoRequest {

    private final String idProduto;
    private final int quantidade;
    private final TipoMovimentacao tipo;

    public String getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public MovimentacaoRequest(String idProduto, int quantidade, TipoMovimentacao tipo) {

        if (idProduto == null || idProduto.isEmpty()) {
            throw new IllegalArgumentException("ID do produto não pode ser vazio.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de movimentação não pode ser nulo.");
        }
        
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }
}
