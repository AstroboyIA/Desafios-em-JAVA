package desafio05.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    List<Produto> produtos = new ArrayList<>();

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
}
