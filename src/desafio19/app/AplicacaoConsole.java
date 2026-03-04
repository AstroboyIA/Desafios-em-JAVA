package desafio19.app;

import java.util.Scanner;

import desafio19.dto.ResultadoAutorizacaoDTO;
import desafio19.dto.TransacaoDTO;
import desafio19.model.ContaCartao;
import desafio19.model.enums.CanalTransacao;
import desafio19.model.enums.CategoriaTransacao;
import desafio19.model.enums.PerfilRiscoCliente;
import desafio19.model.enums.StatusAutorizacao;
import desafio19.service.AutorizacaoService;

public class AplicacaoConsole {

    public void iniciar() {

        Scanner sc = new Scanner(System.in);

        AutorizacaoService service = new AutorizacaoService();

        boasVindas();

        ContaCartao conta = criarConta(sc);

        TransacaoDTO dto = criarTransacao(sc);

        ResultadoAutorizacaoDTO resultado = service.processarTransacao(dto, conta);

        resumoFinal(resultado);

        sc.close();
    }

    private void boasVindas() {
        System.out.println("--- Bem vindo ao Sistema de Autorização de Transações! ---");
    }

    private ContaCartao criarConta(Scanner sc) {

        System.out.println("Para validar a transação, informe os dados abaixo: ");

        System.out.println("Número da conta: ");
        String numero = sc.nextLine();

        System.out.println("Perfil de risco: ");
        System.out.println("1 - BAIXO");
        System.out.println("2 - MODERADO");
        System.out.println("3 - ALTO");

        int opcao = sc.nextInt();
        sc.nextLine();

        PerfilRiscoCliente perfil = PerfilRiscoCliente.fromOpcao(opcao);

        System.out.println("Limite da conta: ");
        double limite = sc.nextDouble();
        sc.nextLine();

        return new ContaCartao(numero, perfil, limite);
    }

    private TransacaoDTO criarTransacao(Scanner sc) {

        System.out.println("ID da transação: ");
        String id = sc.nextLine();

        System.out.println("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.println("Categoria da transação: ");
        System.out.println("1 - ALIMENTAÇÃO");
        System.out.println("2 - ELETRÔNICOS");
        System.out.println("3 - JÓIAS");
        System.out.println("4 - SERVIÇOS");
        System.out.println("5 - INTERNACIONAL");

        int opcaoCategoria = sc.nextInt();
        sc.nextLine();

        CategoriaTransacao categoria = CategoriaTransacao.fromOpcao(opcaoCategoria);

        System.out.println("Canal da transação: ");
        System.out.println("1 - PRESENCIAL");
        System.out.println("2 - ONLINE");
        System.out.println("3 - APP");
        System.out.println("4 - INTERNACIONAL ONLINE");

        int opcaoCanal = sc.nextInt();
        sc.nextLine();

        CanalTransacao canal = CanalTransacao.fromOpcao(opcaoCanal);

        System.out.println("Minuto do dia: ");
        int minuto = sc.nextInt();
        sc.nextLine();

        return new TransacaoDTO(id, valor, categoria, canal, minuto);
    }

    private void resumoFinal(ResultadoAutorizacaoDTO resultado) {

        System.out.println("Status: " + resultado.status().getStatusAutorizacao());
        System.out.println("Score: " + resultado.score());
        System.out.println("Nível: " + resultado.nivelFraude().getDescricao());
        System.out.println("Criticidade: " + resultado.criticidade().getDescricao());
        System.out.println("Exposição: " + resultado.indiceExposicao() + "%");

    }
}