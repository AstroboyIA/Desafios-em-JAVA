package desafio04v2.poo.heranca;

public class Paciente{
    private String nome;
    private Byte idade;

    public Paciente(String nome, Byte idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public void exibirDados(){
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}
