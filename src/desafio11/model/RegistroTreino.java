package desafio11.model;

import java.util.ArrayList;
import java.util.List;

public class RegistroTreino {
    

    List<Treino> treinos = new ArrayList<>();

    public void adicionarTreino(Treino treino){
        treinos.add(treino);
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public int fullTimeTreino(){
        int fulltime = 0;
        for (Treino treino : treinos){
            fulltime += treino.getDuracaoMinutos();
        }
        return fulltime;
    }
    public boolean isTempoTotal(){
        return fullTimeTreino() >= 300;
    }
}
