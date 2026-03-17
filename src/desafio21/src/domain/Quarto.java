package desafio21.src.domain;

import desafio21.src.domain.enums.TipoQuarto;

public class Quarto {
    
    private final String numero;
    private final TipoQuarto tipo;
    private final double tarifaBase;
    private final int capacidadeMaxima;
    private boolean disponivel;
    
    public String getNumero() {
        return numero;
    }
    public TipoQuarto getTipo() {
        return tipo;
    }
    public double getTarifaBase() {
        return tarifaBase;
    }
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    public boolean isDisponivel() {
        return disponivel;
    }

    public Quarto(String numero, TipoQuarto tipo, double tarifaBase, int capacidadeMaxima, boolean disponivel) {

        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("O número do quarto não pode ficar vazio.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do quarto precisa ser informado.");
        }

        if (tarifaBase <= 0) {
            throw new IllegalArgumentException("A tarifa base tem que ser maior que 0.");
        }

        if (capacidadeMaxima < 1) {
            throw new IllegalArgumentException("A capacidade máxima não pode ser menor que 1.");
        }
        
        this.numero = numero;
        this.tipo = tipo;
        this.tarifaBase = tarifaBase;
        this.capacidadeMaxima = capacidadeMaxima;
        this.disponivel = true;
    }


}
