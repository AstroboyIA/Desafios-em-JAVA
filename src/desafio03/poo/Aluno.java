package desafio03.poo;

import desafio03.poo.heranca.Pessoa;

public class Aluno extends Pessoa {
    private double nota;

    public Aluno(String nome, int id, double nota) {
        super(nome, id);
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }


    @Override
    public void exibirInfo(){
        super.exibirInfo();
        System.out.println("Nota: " + getNota());
        if (nota >= 6.0){
            System.out.println("Aluno Aprovado!");
        }else{
            System.out.println("Aluno Reprovado!");
        }
    }
}