public class ContaBancaria {
    String titular;
    int numeroConta;
    double saldo = 0.0;

    //metodo para depositar

    public void depositar ( double valor) {
        if(valor > 0){
            saldo += valor;
            System.out.println("Depositando RS"+ valor + " ... Deposito realizado");
        }else{
            System.out.println("Valor inválido, tente novamente!");
        }
    }

    //metodo para sacar

    public void sacar ( double valor) {
        if (valor <= saldo){
            saldo -= valor;
            System.out.println("Sacando R$: " + valor + " ... Saque realizado");
            }else{
                System.out.println("Saldo insuficiente, tente novamente!");
            }
        }

    //metodo para exibir dados da conta

    public void exibirDados(){
        System.out.println("Titular: " + titular);
        System.out.println("Saldo atual: " + saldo);
    }
}
