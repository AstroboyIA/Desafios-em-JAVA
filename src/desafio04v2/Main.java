package desafio04v2;

import desafio04v2.poo.Consulta;
import desafio04v2.poo.Exame;
import desafio04v2.poo.PacienteVip;
import desafio04v2.poo.heranca.Paciente;
import desafio04v2.poo.interfaces.Atendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entradaUsuario = new Scanner(System.in);

        List<Paciente> pacientes = new ArrayList<>();

        Paciente paciente = null;
        Atendimento servico = null;

        System.out.println(" --Bem vindo ao sistema de gestão da Clínica-- ");
        System.out.println("\n");
        System.out.println("Qual o nome do paciente?");
        String nome = entradaUsuario.nextLine();
        System.out.println("Qual a idade do paciente?");
        Byte idade = entradaUsuario.nextByte();
        entradaUsuario.nextLine();

        System.out.println(" " +
                "O paciente é VIP? \n " +
                "1 - Sim \n " +
                "2 - Não");
        int resposta = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        if (resposta == 1) {
            System.out.println("Qual o beneficio desse paciente ?");
            String beneficioExtra = entradaUsuario.nextLine();
            paciente = new PacienteVip(nome, idade, beneficioExtra);
        } else {
            paciente = new Paciente(nome, idade);
        }
        if (paciente != null) {
            pacientes.add(paciente);
        }

        System.out.println("Paciente adicionado com sucesso!");

        System.out.println("Qual serviço o paciente ira utilizar hoje?");
        System.out.println(" " +
                "1 - Exame \n " +
                "2 - Consulta");
        int opcao = entradaUsuario.nextInt();

        switch (opcao) {
            case 1:
                servico = new Exame();
                break;
            case 2:
                servico = new Consulta();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        if (servico != null) {
            paciente.exibirDados();
            servico.realizarProcedimento();
        }
    entradaUsuario.close();
    }
}