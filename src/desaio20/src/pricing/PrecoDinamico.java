package desaio20.src.pricing;

import desaio20.src.domain.Produto;
import desaio20.src.repository.ProdutoRepository;
import desaio20.src.repository.ProdutosRepositoryEmMemoria;

public class PrecoDinamico implements Precificavel {

    private final int estoqueAlvo;
    private final ProdutoRepository repository;

    public PrecoDinamico(int estoqueAlvo, ProdutoRepository repository) {

        if (estoqueAlvo < 0) {
            throw new IllegalArgumentException("O estoque alvo não pode ser negativo.");
        }

        if (repository == null) {
            throw new IllegalArgumentException("O repository não pode ser nulo.");
        }

        this.estoqueAlvo = estoqueAlvo;
        this.repository = repository;
    }

    @Override
    public double calcularPrecoFinal(double precoBase) {

        int estoqueAtual = buscarMenorEstoqueDisponivel();

        if (estoqueAtual <= estoqueAlvo) {
            return precoBase * 1.30;
        } else {
            return precoBase * 0.90;
        }
    }

    @Override
    public String descricaoEstrategia() {
        return String.format("Preço dinâmico — alvo de estoque: %d unidades (+30%% escassez / -10%% excesso)", estoqueAlvo);
    }

    private int buscarMenorEstoqueDisponivel() {
        return repository.buscarTodos()
                .stream()
                .mapToInt(Produto::getQuantidadeEmEstoque)
                .min()
                .orElse(0);
    }
}
