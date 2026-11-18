package aplicacao;
import modelo.CaixaCentral;
public class TesteSingleton {
    public static void main(String[] args){
        CaixaCentral caixa1 = CaixaCentral.getInstancia();
        CaixaCentral caixa2 = CaixaCentral.getInstancia();

        if (caixa1 != caixa2){
            throw new IllegalStateException("Foram criados caixas diferentes.");
        }
        System.out.println("OK - Os dois acessos retornam o mesmo objeto.");

        double saldoInicial = caixa1.getSaldoTotal();

        caixa1.adicionarValor(10.00);
        if (caixa2.getSaldoTotal() != saldoInicial + 10.00){
            throw new IllegalStateException("O saldo nao foi compartilhado.");
        }

        caixa2.adicionarValor(20.00);
        if (caixa1.getSaldoTotal() != saldoInicial + 30.00){
            throw new IllegalStateException("O saldo ficou inconsistente.");
        }
        System.out.println("OK - Os dois acessos compartilham o saldo.");
        System.out.printf("Saldo final: R$ %.2f%n", caixa1.getSaldoTotal());
    }
}