package desafio21.src.pricing;

public interface TarifaCalculavel {
    
    double calcularValor (double tarifaBase, int diarias) ;

    String descricaoTarifa () ;
    
}
