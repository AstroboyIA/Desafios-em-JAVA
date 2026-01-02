package desafio02;

import desafio02.poo.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        ArrayList<Produto> estoque = new ArrayList<>();
        //cria uma lista ( preciso estudar mais a criação de lista, precisei consultar para ter certeza do que fazer


        System.out.println("Bem vindo ao gerenciador do Estoque!");

        while (true) {
            System.out.println("Selecione uma opção: \n" +
                    "1 - Adicionar um Produto. \n" +
                    "2 - Mostrar o estoque atual. \n" +
                    "3 - Sair.");
            int opcao = entrada.nextInt();

            //para o while se escolher 3
            if (opcao == 3) break;

            //inicia a criação do produto
            if (opcao == 1) {
                System.out.println("Digite o nome do produto: ");
                String nome = entrada.nextLine();
                System.out.println("Digite o valor do produto: ");
                double preco = entrada.nextDouble();
                System.out.println("Digite o quantidade do produto: ");
                int quantidade = entrada.nextInt();
                // motra os produtos em estoque
            } else if (opcao == 2) {
                for ( Produto p : estoque) {
                    System.out.println(
                            "Produto:" + p.getNome()+"\n"+
                            "Preço" +p.getPreco()+ "\n"+
                            "Quantidade" +p.getQuantidade());
                }
            }
        }
        entrada.close();
    }
}