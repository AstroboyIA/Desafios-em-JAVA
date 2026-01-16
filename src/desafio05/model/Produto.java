package desafio05.model;

public class Produto {
    String infoProduto;
    Double infoValor; // preco nao pode ser negativo

  public Double getInfoValor() {
        return infoValor;
    }

  public Produto (String infoProduto, Double infoValor) {
        this.infoProduto = infoProduto;
        this.infoValor = infoValor;
    }
}
