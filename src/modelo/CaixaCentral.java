package modelo;

public class CaixaCentral {
 
    private static CaixaCentral instanciaUnica;
    private double saldoTotal;

    private CaixaCentral() {
        this.saldoTotal = 0.0;
    }

    public static synchronized CaixaCentral getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new CaixaCentral();
        }
        return instanciaUnica;
    }

    public void adicionarValor(double valor) {
        if (valor > 0) {
            this.saldoTotal += valor;
            System.out.println("[CAIXA] R$ " + valor + " adicionados com sucesso.");
        }
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }
}