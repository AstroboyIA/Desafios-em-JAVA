package desaio20.src.app;

import java.util.List;

import desaio20.src.dto.CadastrarProdutoRequest;
import desaio20.src.listener.AlertaReposicaoListener;
import desaio20.src.listener.LogListener;
import desaio20.src.listener.RelatoriosEventosListener;
import desaio20.src.pricing.Precificavel;
import desaio20.src.pricing.PrecoNormal;
import desaio20.src.repository.ProdutoRepository;
import desaio20.src.repository.ProdutosRepositoryEmMemoria;
import desaio20.src.service.EstoqueService;
import desaio20.src.service.PrecificacaoService;

public class Main {
    public static void main(String[] args) {
        
        ProdutoRepository repository = new ProdutosRepositoryEmMemoria();

        LogListener log = new LogListener();
        AlertaReposicaoListener alerta = new AlertaReposicaoListener();
        RelatoriosEventosListener relatorioEventos = new RelatoriosEventosListener();

        Precificavel estrategia = new PrecoNormal();
        PrecificacaoService precificacao = new PrecificacaoService(estrategia);

        EstoqueService service = new EstoqueService(repository, precificacao, List.of(log, alerta, relatorioEventos));

        service.cadastrarProduto(, repository);

        service.movimentarEstoque();

        service.gerarRelatorio();
    }

    
}
