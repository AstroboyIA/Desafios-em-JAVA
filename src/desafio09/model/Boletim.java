package desafio09.model;

import java.util.ArrayList;
import java.util.List;

public class Boletim {
    
    private List<Nota> notas = new ArrayList<>();

    public List<Nota> getNotas() {
        return notas;
    }

    public void adicionarNota(Nota nota) {
        this.notas.add(nota);
    }

    public double calcularMedia(){
        double nota = 0;
        for (Nota n : notas) {
            nota += n.getValor();
        }
        double media = nota / notas.size();
        return media;
    }

    public boolean isAprovado(){
        double media = calcularMedia();

        if (media >= 7.0) {
            return true;
        } else {
            return false;
        }
    }
}