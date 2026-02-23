package desafio16;

import java.util.Scanner;

import desafio16.model.ChamadoSuporte;
import desafio16.service.AtendimentoService;


public class Main {
    public static void main(String[] args) {
        
        AtendimentoService service = new AtendimentoService();
        
        Scanner sc = new Scanner(System.in);

        service.BoasVindas();

        ChamadoSuporte chamado = service.criarChamado(sc);

        service.criarAtividadeAtendimento(sc, chamado);

        service.resumoFinal(chamado);

        sc.close();

    }
}