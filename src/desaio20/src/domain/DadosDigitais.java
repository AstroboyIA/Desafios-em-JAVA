package desaio20.src.domain;

public class DadosDigitais implements DadosAdicionais {

    private final String urlDownload;
    private final double tamanhoMb;
    private final String plataforma;

    public DadosDigitais(String urlDownload, double tamanhoMb, String plataforma) {
        this.urlDownload = urlDownload;
        this.tamanhoMb = tamanhoMb;
        this.plataforma = plataforma;
    }

    public String resumo() {
        return 
        "Link para download: " + urlDownload +
        "Tamanho do arquivo: " + tamanhoMb +
        "Plataforma: " + plataforma;
    }
}
