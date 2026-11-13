package Exercicio01;

import java.util.ArrayList;

public class MinhaAgendaDeAniversarios implements AgendaDeAniversarios {

    private ArrayList<Aniversariante> aniversariantes;

    public MinhaAgendaDeAniversarios() {
        this.aniversariantes = new ArrayList<>();
    }

    @Override
    public void adicionarAniversariante(String nome, int dia, int mes) {
        Aniversariante a = new Aniversariante(nome, dia, mes);
        this.aniversariantes.add(a);
    }

    @Override
    public ArrayList<String> obterAniversariantesDoDia(int dia, int mes) {
        ArrayList<String> nomes = new ArrayList<>();
        for (Aniversariante a : this.aniversariantes) {
            if (a.getDataAniversario().getDia() == dia && a.getDataAniversario().getMes() == mes) {
                nomes.add(a.getNome());
            }
        }
        return nomes;
    }

    @Override
    public void removerAniversariante(String nomeAniversariante) {
        Aniversariante paraRemover = null;
        for (Aniversariante a : this.aniversariantes) {
            if (a.getNome().equalsIgnoreCase(nomeAniversariante)) {
                paraRemover = a;
                break;
            }
        }
        if (paraRemover != null) {
            this.aniversariantes.remove(paraRemover);
        }
    }
}