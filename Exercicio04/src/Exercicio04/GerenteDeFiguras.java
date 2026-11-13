package Exercicio04;

import java.util.ArrayList;
import java.util.List;

public class GerenteDeFiguras {
    private List<FiguraGeometrica> figuras;

    public GerenteDeFiguras() {
        figuras = new ArrayList<FiguraGeometrica>();
    }

    public void adicionaFigura(FiguraGeometrica fig) {
        this.figuras.add(fig);
    }

    public List<FiguraGeometrica> getFiguras() {
        return this.figuras;
    }

    public double calculaAreaTotalDeFiguras() {
        double total = 0.0;
        for (FiguraGeometrica fig : figuras) {
            total += fig.calculaArea();
        }
        return total;
    }

    public void imprimeFiguras() {
        System.out.println("--- Lista de Figuras Cadastradas ---");
        for (FiguraGeometrica fig : figuras) {
            System.out.println("- " + fig.getNomeFigura() + " (Área: " + fig.calculaArea() + ")");
        }
    }

    public double getMaiorAreaDeFigura() {
        if (figuras.isEmpty()) {
            return 0.0;
        }
        
        double maiorArea = figuras.get(0).calculaArea();
        for (FiguraGeometrica fig : figuras) {
            if (fig.calculaArea() > maiorArea) {
                maiorArea = fig.calculaArea();
            }
        }
        return maiorArea;
    }
}