package desafio08.model;

import java.util.ArrayList;
import java.util.List;

public class Matricula {

    List<Curso> cursos = new ArrayList<>();

    public void adicionarCursos(Curso curso) {
        cursos.add(curso);
    }

    public int totalCargaHoraria() {
        int total = 0;
        for (Curso curso : cursos) {
            total += curso.getCargaHoraria();
        }
        return total;
    }

    public boolean classificarMatricula() {
        return totalCargaHoraria() > 100;
    }
}