package desafio19.app;

import java.util.Scanner;

import desafio19.dto.TransacaoDTO;
import desafio19.model.ContaCartao;
import desafio19.model.enums.StatusAutorizacao;
import desafio19.service.AutorizacaoService;

public class AplicacaoConsole {

    public void iniciar() {
        
        Scanner sc = new Scanner(System.in);
        AutorizacaoService service = new AutorizacaoService();

        ContaCartao conta = criarConta(sc);

        TransacaoDTO dados = new coletarTransacao(sc);

        StatusAutorizacao status = service.processarTransacao(

                dados.id(),
                dados.valor(),
                dados.categoria(),
                dados.canal(),
                dados.minutosDoDia(),
                conta

        );

        System.out.println("Status: " + status);
    }
}
