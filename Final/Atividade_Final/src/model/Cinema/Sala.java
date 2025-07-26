package model.Cinema;

public class Sala {
    private int numeroSala;
    private Assento[][] assentos;
    private Filme filme;

    public Sala(int numeroSala) {
        this.numeroSala = numeroSala;
        this.assentos = new Assento[20][10];
        inicializarAssentos();
    }

    private void inicializarAssentos() {
        for (int i = 0; i < 20; i++) {
            char fileira = (char) ('A' + i);
            for (int j = 0; j < 10; j++) {
                assentos[i][j] = new Assento(fileira, j + 1);
            }
        }
    }

    public void mostrarMapaAssentos() {
        if (filme == null) {
            System.out.println("Nenhum filme cadastrado nesta sala.");
            return;
        }
        System.out.println("\nMapa de Assentos - Sala " + numeroSala);
        System.out.print("   ");
        for (int j = 1; j <= 10; j++) {
            System.out.print(String.format(" %02d  ", j));
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            char fileira = (char) ('A' + i);
            System.out.print(fileira + "  ");
            for (int j = 0; j < 10; j++) {
                System.out.print(assentos[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("[XX] = Ocupado, [  ] = Livre");
        
    }


    public Assento getAssento(char fileira, int numero) {
        return assentos[fileira - 'A'][numero - 1];
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }


    public Filme getFilme() {
        return filme;
    }

    public int getNumeroSala() {
        return numeroSala;
    }
}
