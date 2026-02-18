package main.aprendendoTestesAutomatizados.model;

public class calculadoraDesconto {
    double valorOriginal;
    double percentual;

    public double calcularDesconto() {

        double valorComDesconto = 0.0;

        valorComDesconto = valorOriginal - (valorOriginal * percentual / 100);

        if (valorOriginal > 0) {
            System.out.println("O valor não pode ser negativo.");
            throw new IllegalArgumentException();
        }

        if (percentual < 0 || percentual > 100) {
            System.out.println("O percentual deve ser um valor entre 0 e 100.");
            throw new IllegalArgumentException();
        }
        
        return valorComDesconto;
    }
}
