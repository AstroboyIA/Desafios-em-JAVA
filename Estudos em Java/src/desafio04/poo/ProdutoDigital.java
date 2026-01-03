package desafio04.poo;

import desafio04.poo.heranca.Produto;

public class ProdutoDigital extends Produto {
    private String linkDownload;

    public ProdutoDigital(String nome, double precoBase,String linkDownload) {
        super(nome, precoBase);
        this.linkDownload = linkDownload;
    }
}

