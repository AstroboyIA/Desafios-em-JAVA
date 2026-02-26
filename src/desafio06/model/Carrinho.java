package desafio06.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void mensagemFinal() {
        double total = calcularTotal();

        if (total > 100) {
            System.out.println("Compra de valor alto!");
        } else {
            System.out.println("Compra comum!");
        }
    }
}

// Finalizado