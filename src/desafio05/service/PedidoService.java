package desafio05.service;

import java.util.Scanner;
import desafio05.model.Cliente;
import desafio05.model.ClienteVip;
import desafio05.model.Pedido;
import desafio05.model.Produto;

public class PedidoService {

    public Cliente cadastrarCliente(Scanner sc) {

        String nomeCliente;
        byte tipoCliente;
        Cliente cliente = null;
        ClienteVip clienteVip = null;

        do {

            System.out.println("Selecione o tipo de atendimento para o Cliente:");
            System.out.println("1 - Cliente novo");
            System.out.println("2 - Cliente VIP");
            tipoCliente = sc.nextByte();
            sc.nextLine();

            if (tipoCliente == 1 || tipoCliente == 2) {
                do {
                    System.out.println("Digite o nome do cliente:");
                    nomeCliente = sc.nextLine().trim();
                    if (nomeCliente.isEmpty()) {
                        System.out.println("Por favor, digite o nome do cliente!");
                    }
                } while (nomeCliente.isEmpty()); // Repete o loop até inserir um nome

                if (tipoCliente == 1) {

                    cliente = new Cliente(nomeCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    return cliente;
                } else {
                    clienteVip = new ClienteVip(nomeCliente);
                    System.out.println("Cliente VIP cadastrado com sucesso!");
                    return clienteVip;
                }

            } else {
                System.out.println("Opção inválida, por favor, tente novamente!");
            }
        } while (tipoCliente != 1 && tipoCliente != 2);

        return null;
    }

    public Pedido gerenciarPedido(Scanner sc, Cliente cliente) {

        Pedido pedido = new Pedido();
        byte opcao;
        byte continuar;

        do {
            System.out.println("Você deseja adicionar itens ao pedido do cliente agora?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            opcao = sc.nextByte();
            sc.nextLine();

            if (opcao == 1) {
                do {
                    System.out.println("Qual o produto do cliente?");
                    String infoProduto = sc.nextLine().trim();

                    System.out.println("Qual o valor do produto ?");
                    Double infoValor = sc.nextDouble();
                    sc.nextLine();

                    Produto produto = new Produto(infoProduto, infoValor);
                    pedido.addProduto(produto);
                    System.out.println("Produto adicionado com sucesso.");

                    System.out.println("Você deseja adicionar mais um protudo?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    continuar = sc.nextByte();
                    sc.nextLine();

                } while (continuar == 1);
                System.out.println("Finalizando pedido...");

            } else if (opcao == 2) {
                System.out.println("Atendimento encerrado!");

            } else {
                System.out.println("Opção inválida, digite uma opção válida!");
            }
        } while (opcao != 1 && opcao != 2);
        return pedido;
    }

    public void exibirResumo(Cliente cliente, Pedido pedido) {
        System.out.println("Aqui está a comanda finalizada para o seu cliente:");
        System.out.println("Cliente : " + cliente.getNomeCliente());

        if (cliente instanceof ClienteVip) {
            ClienteVip clienteVip = (ClienteVip) cliente;
            Double valorFinalDesconto = clienteVip.calcularValorFinal(pedido);
            System.out.println("Valor total do pedido : " + valorFinalDesconto);
        } else {
            Double valorFinal = pedido.contaValorTotal();
            System.out.println("Valor total do pedido : " + valorFinal);
        }
    }

}