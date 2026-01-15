package desafio04.poo;

import desafio04.poo.Interfaces.Pagamento;

public class Pix implements Pagamento {
    @Override
    public void processarPagamento(double valor) {
        valor = valor * 0.9;
        System.out.println("Pagamento no valor de R$ " +valor+  " realizado com sucesso! (Desocnto de 10% aplicado) ");
    }
}
