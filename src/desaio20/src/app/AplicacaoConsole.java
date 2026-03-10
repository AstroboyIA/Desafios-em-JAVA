package desaio20.src.app;

import java.util.List;
import java.util.Scanner;

import desaio20.src.listener.AlertaReposicaoListener;
import desaio20.src.listener.LogListener;
import desaio20.src.listener.RelatorioEventosListener;
import desaio20.src.pricing.Precificavel;
import desaio20.src.pricing.PrecoNormal;
import desaio20.src.repository.ProdutoRepository;
import desaio20.src.repository.ProdutoRepositoryEmMemoria;
import desaio20.src.service.EstoqueService;
import desaio20.src.service.PrecificacaoService;

public class AplicacaoConsole {

    public void iniciar() {

        Scanner sc = new Scanner(System.in);

        ProdutoRepository repository = new ProdutoRepositoryEmMemoria();

        LogListener log = new LogListener();
        AlertaReposicaoListener alerta = new AlertaReposicaoListener();
        RelatorioEventosListener relatorioEventos = new RelatorioEventosListener();

        Precificavel estrategia = new PrecoNormal();
        PrecificacaoService precificacao = new PrecificacaoService(estrategia);

        EstoqueService service = new EstoqueService(repository, precificacao, List.of(log, alerta, relatorioEventos));

        service.cadastrarProduto(null);

        service.movimentarEstoque(null);

        service.gerarRelatorio();
    }
}
