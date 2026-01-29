package desafio10;

import java.util.Scanner;

import desafio10.model.Funcionario;
import desafio10.model.Relatorio;
import desafio10.service.RelatorioService;

public class Main {
    public static void Main(String[] args) {

        RelatorioService relatorioService = new RelatorioService();

        Funcionario funcionario = new Funcionario("");
        Relatorio relatorio = new Relatorio();


        Scanner sc = new Scanner(System.in);

        relatorioService.welcome();

        relatorioService.cadastrarFuncionario(sc, funcionario);

        relatorioService.cadastrarNotas(sc, relatorio);

        relatorioService.exibirSatisfacao(relatorio);
    }
}
