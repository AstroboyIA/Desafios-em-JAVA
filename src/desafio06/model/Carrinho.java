package desafio06.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    
    List<Produto> produtos = new ArrayList<>();

    public List<Produto> getProdutos(){
        return produtos;
    }

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }

    public Double calcularTotal(){
        Double total = 0.0;
        for (Produto produto : produtos){
            total += produto.getPreco();
        }
        return total;
    }
}


// Finalizado