package desaio20.src.domain;

import java.util.List;
import java.util.function.Consumer;

public class DadosFisicos implements DadosAdicionais {

    private final double pesoKg;
    private final String dimensoesCm;
    private final boolean requerRefrigeracao;

    public DadosFisicos(double pesoKg, String dimensoesCm, boolean requerRefrigeracao) {
        this.pesoKg = pesoKg;
        this.dimensoesCm = dimensoesCm;
        this.requerRefrigeracao = requerRefrigeracao;
    }

    @Override
    public String resumo() {
        return 
        "Peso : " + pesoKg +
        "Dimensões: " + dimensoesCm +
        "Precisa de refrigeração?: " + requerRefrigeracao;
    }

    @Override
    public void verificarAlertas(Produto<?> produto, List<String> alertas, Consumer<EventoEstoque> publicador) {}
}
