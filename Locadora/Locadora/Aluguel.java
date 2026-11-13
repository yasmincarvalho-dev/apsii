
public class Aluguel {
    private int diasAlugada;
    private Fita fita;

    public Aluguel(Fita fita, int diasAlugada) {
        this.fita = fita;
        this.diasAlugada = diasAlugada;
    }

    public Fita getFita() {
        return fita;
    }

    public int getDiasAlugada() {
        return diasAlugada;
    }

    public double getValor() {
        double valorCorrente = 0;
        switch (getFita().getCodigoDePreco()) {
            case Fita.NORMAL:
                valorCorrente += 2;
                if (getDiasAlugada() > 2)
                    valorCorrente += (getDiasAlugada() - 2) * 1.5;
                break;
            case Fita.LANCAMENTO:
                valorCorrente += getDiasAlugada() * 3;
                break;
            case Fita.INFANTIL:
                valorCorrente += 1.5;
                if (getDiasAlugada() > 3)
                    valorCorrente += (getDiasAlugada() - 3) * 1.5;
                break;
        }
        return valorCorrente;
    }

    public int getPontosDeAlugadorFrequente() {
        if ((getFita().getCodigoDePreco() == Fita.LANCAMENTO) && getDiasAlugada() > 1) {
            return 2;
        }
        return 1;
    }
}
