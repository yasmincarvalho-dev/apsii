package pagamento;
import modelo.CaixaCentral;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PagamentoDinheiro implements Pagamento{
    private final CaixaCentral caixa = CaixaCentral.getInstancia();
    private final double valorRecebido;
    public PagamentoDinheiro(double valorRecebido){
        this.valorRecebido = valorRecebido;
    }

    @Override
    public boolean processarPagamento(double valor){
        if (!Double.isFinite(valor) || valor <= 0
                || !Double.isFinite(valorRecebido) || valorRecebido < 0){
            System.out.println("Valor do pedido ou do dinheiro invalido.");
            return false;
        }

        BigDecimal total = BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal recebido = BigDecimal.valueOf(valorRecebido)
                .setScale(2, RoundingMode.HALF_UP);

        if (total.signum() == 0){
            System.out.println("O total deve ser de pelo menos um centavo.");
            return false;
        }

        if (recebido.compareTo(total) < 0){
            System.out.println("Dinheiro insuficiente. Pagamento recusado.");
            return false;
        }

        BigDecimal troco = recebido.subtract(total);
        caixa.adicionarValor(total.doubleValue());
        System.out.printf("Pagamento em dinheiro: R$ %.2f | Recebido: R$ %.2f | Troco: R$ %.2f%n", total, recebido, troco);
        return true;
    }
}