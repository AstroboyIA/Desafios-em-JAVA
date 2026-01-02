package desafio02;

import desafio02.poo.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        ArrayList<Produto> estoque = new ArrayList<>();
        //cria uma lista ( preciso estudar mais a criação de lista, precisei consultar para ter certeza do que fazer

        boolean continuar = true;

        System.out.println("Bem vindo ao gerenciador do Estoque!");

        while (continuar) {
            System.out.println("Selecione uma opção: \n" +
                    "1 - Adicionar um Produto. \n" +
                    "2 - Mostrar o estoque atual. \n" +
                    "3 - Sair.");
            int opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    // recebe os dados do usuario
                    System.out.println("Digite o nome do produto: ");
                    String nome = entrada.nextLine();
                    System.out.println("Digite o valor do produto: ");
                    double preco = entrada.nextDouble();
                    entrada.nextLine();
                    System.out.println("Digite o quantidade do produto: ");
                    int quantidade = entrada.nextInt();
                    entrada.nextLine();

                    // cria o produto na lista
                    Produto p = new Produto();
                    p.setNome(nome);
                    p.setPreco(preco);
                    p.setQuantidade(quantidade);
                    estoque.add(p);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    if (estoque.isEmpty()) {
                        System.out.println("Nenhum produto foi encontrado.");
                    } else {
                        System.out.println("\n--- Estoque Atual ---");
                        for (Produto produto : estoque) {
                            produto.exibirDetalhes();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Encerrando o programa.");
                    continuar = false;
                    break;


                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }
        entrada.close();
    }
}
