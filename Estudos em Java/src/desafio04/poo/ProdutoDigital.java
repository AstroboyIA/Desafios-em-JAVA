package desafio04.poo;

import desafio04.poo.heranca.Produto;

public class ProdutoDigital extends Produto {
    private String linkDownload;

    public ProdutoDigital(String nome, double precoBase,String linkDownload) {
        super(nome, precoBase);
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    @Override
    public void exibirDados(){
        super.exibirDados();
        System.out.println("Link download: " + this.linkDownload);
    }
}

