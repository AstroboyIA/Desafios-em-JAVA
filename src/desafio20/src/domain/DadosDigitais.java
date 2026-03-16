package desafio20.src.domain;

import java.util.List;
import java.util.function.Consumer;

public class DadosDigitais implements DadosAdicionais {

    private final String urlDownload;
    private final double tamanhoMb;
    private final String plataforma;

    public DadosDigitais(String urlDownload, double tamanhoMb, String plataforma) {
        this.urlDownload = urlDownload;
        this.tamanhoMb = tamanhoMb;
        this.plataforma = plataforma;
    }

    @Override
    public String resumo() {
        return 
        "Link para download: " + urlDownload +
        "Tamanho do arquivo: " + tamanhoMb +
        "Plataforma: " + plataforma;
    }

    @Override
    public void verificarAlertas(Produto<?> produto, List<String> alertas, Consumer<EventoEstoque> publicador) {}
}
