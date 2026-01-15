package desafio04;

import desafio04.poo.CartaoCredito;
import desafio04.poo.Interfaces.Pagamento;
import desafio04.poo.Pix;
import desafio04.poo.ProdutoDigital;
import desafio04.poo.heranca.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entradaUsuario = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<Produto>();

        System.out.println("-- Bem vindo ao Sistema de Checkout! --");
        System.out.print("\n");

        System.out.println("Qual produto você deseja adquirir hoje? ");
        String nome = entradaUsuario.nextLine();

        System.out.println("Qual o valor do produto? ");
        double precoBase = entradaUsuario.nextDouble();
        entradaUsuario.nextLine();

        System.out.print("O produto é: \n" +
                "1 - Digital \n" +
                "2 - fisico");

        int tipoProduto = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        Produto produto = null;

        if (tipoProduto == 1) {
            System.out.println("Digite o link do produto: ");
            String linkDownload = entradaUsuario.nextLine();
            produto = new ProdutoDigital(nome, precoBase, linkDownload);
        } else {
            produto = new Produto(nome, precoBase);
        }

        if (produto != null) {
            produtos.add(produto);

            System.out.println(" -Produto selecionado com sucesso!- ");
            System.out.println("\n");

            System.out.println("Qual será a sua forma de pagamento?");
            System.out.println(
                    "1 - Cartão de Crédito \n" +
                            "2 - Pix");

            int opcao = entradaUsuario.nextInt();
            entradaUsuario.nextLine();

            Pagamento metodoPagamento = null;

            switch (opcao) {
                case 1:
                    metodoPagamento = new CartaoCredito();
                    break;

                case 2:
                    metodoPagamento = new Pix();
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
            if (metodoPagamento != null) {
                System.out.println("\n --Resumo da compra--");
                produto.exibirDados();

                metodoPagamento.processarPagamento(produto.getPrecoBase());
            }
            entradaUsuario.close();
        }
    }
}