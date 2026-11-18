package pagamento;
import modelo.CaixaCentral;
import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PagamentoPix implements Pagamento{
    private final CaixaCentral caixa = CaixaCentral.getInstancia();

    @Override
    public boolean processarPagamento(double valor){
        if (!Double.isFinite(valor) || valor <= 0) {
            System.out.println("Valor do pedido invalido.");
            return false;
        }

        BigDecimal total = BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP);
        if (total.signum() == 0){
            System.out.println("O total deve ser de pelo menos um centavo.");
            return false;
        }

        caixa.adicionarValor(total.doubleValue());
        System.out.printf("Pagamento via Pix aprovado: R$ %.2f.%n", total);
        return true;
    }
}