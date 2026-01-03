package desafio04;

import desafio04.poo.CartaoCredito;
import desafio04.poo.heranca.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entradaUsuario = new Scanner(System.in);

        ArrayList<Produto> produtos = new ArrayList<Produto>();

        System.out.println("-- Bem vindo ao Sistema de Checkout! --");
        System.out.print("\n");
        System.out.println("Qual item você deseja adquirir hoje? ");
        String nome = entradaUsuario.nextLine();
        System.out.println("Qual o valor do item? ");
        double valor = entradaUsuario.nextDouble();
        entradaUsuario.nextLine();
        Produto produto = new Produto();
        produto.setNome(nome);
        System.out.println("Qual será a sua forma de pagamento?");
        System.out.println(
                "1 - Cartão de Crédito \n" +
                "2 - Pix");
        int formaEscolhida =  entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        switch (formaEscolhida){
            case 1:
                formaEscolhida = new CartaoCredito();
            break;
            case 2:

            break;
        }
    }


}