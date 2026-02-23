package desafio17;

import java.util.Scanner;

import desafio17.model.PipelineDeploy;
import desafio17.service.OrquestracaoDeployService;

public class Main {
    public static void main(String[] args) {
        
        OrquestracaoDeployService service = new OrquestracaoDeployService();
        Scanner sc = new Scanner(System.in);

        service.Wellcome();

        PipelineDeploy pipeline = service.criarPipeline(sc);

        service.adicionarEtapa(pipeline, sc);

        service.resumoFinal(pipeline);

        sc.close();
    }
}
