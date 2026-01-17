package desafio05.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    List<Produto> produtos = new ArrayList<>();

    // adiciona produto na lista
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    // remove produto da lista
    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    // getter da lista produtos, eu não sabia que precisava fazer isso :(
    public List<Produto> getProdutos() {
        return produtos;
    }

    public Double contaValorTotal() {
        /// PARA VERIFICAR LISTA VAZIA USAR .isEmpty()
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto adicionado ao pedido.");
            return 0.0;
        }

        /// cria o metodo que vai somar os valores dentro da lista de produtos e retorna
        Double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }
}