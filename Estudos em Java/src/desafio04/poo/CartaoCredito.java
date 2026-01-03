package desafio04.poo;

import desafio04.poo.Interfaces.Pagamento;

public class CartaoCredito implements Pagamento {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("Pagamento no valor de R$ " +valor+  " realizado com sucesso! (Taxa de 5% aplicada)");
    }
}
