package model.Cinema;

import java.util.Scanner;
import model.Sistema;

public class Sala {
    protected  int numeroSala;
    protected  Assento[][] assentos;
    protected  Filme filme;

    public Sala(int numeroSala) {
        this.numeroSala = numeroSala;
        this.assentos = new Assento[20][10];
        inicializarAssentos();
    }
    public Sala() {
        this.numeroSala = 1;
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
    public Sala selecionarSala(Scanner input) {
    try {
        System.out.print("Sala (1 a 5): ");
        int salaNum = input.nextInt();
        input.nextLine();

        if (salaNum < 1 || salaNum > 5) {
            System.out.println("Sala inválida.");
            return null;
        }

        return Sistema.salas[salaNum - 1];
    } catch (Exception e) {
        System.out.println("Entrada inválida. Compra cancelada.");
        input.nextLine(); 
        return null;
    }
}

    public void resetarAssentos() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                assentos[i][j].setOcupado(false);
            }
        }
        System.out.println("Assentos resetados na sala " + numeroSala);
    }
    public void reservarAssento(char fileira, int numero) {
        if (fileira < 'A' || fileira > 'T' || numero < 1 || numero > 10) {
            System.out.println("Assento inválido.");
        }
        else if (filme == null) {
            System.out.println("Nenhum filme cadastrado nesta sala.");
        }
        else {
            Assento assento = getAssento(fileira, numero );
            if (assento.isOcupado()) {
                System.out.println("Assento já está ocupado. Tente outro.");
            } else {
                assento.setOcupado(true);
                System.out.println("Assento " + fileira + numero + " reservado com sucesso na sala " + numeroSala);
            }
        }
    }
    public void cadastrarFilme(Filme filme) {
        if (this.filme == null) {
            this.filme = filme;
            System.out.println("Filme cadastrado na sala " + numeroSala);
        } else {
            System.out.println("Já existe um filme cadastrado nesta sala. Remova o filme atual antes de cadastrar um novo.");
        }
    }
    public void removerFilme(String nomeFilme) {
        if (filme != null && filme.getTitulo().equals(nomeFilme)) {
            filme = null;
            System.out.println("Filme removido da sala " + numeroSala);
            resetarAssentos();
            
        } else {
            System.out.println("Filme não encontrado na sala " + numeroSala);
        }

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

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Assento[][] getAssentos() {
        return assentos;
    }

    public void setAssentos(Assento[][] assentos) {
        this.assentos = assentos;
    }
    @Override
    public String toString() {
        return "Sala " + numeroSala;
    }
}
