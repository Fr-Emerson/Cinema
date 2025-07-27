package model.Cinema;

import java.util.Scanner;

public class Assento {
    private char fileira;
    private int numero;
    private boolean ocupado;

    public Assento(char fileira, int numero) {
        this.fileira = fileira == '\0' ? 'A' : fileira; 
        this.numero = numero < 1 ? 1 : Math.min(numero, 10); 
        this.ocupado = false;
    }
    

    public Assento() {
        this.fileira = ' ';
        this.numero = 0;
        this.ocupado = false;
    }


    public boolean isOcupado() {
        return ocupado;
    }
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    public char getFileira() {
        return fileira;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setFileira(char fileira) {
        this.fileira = fileira;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public  Assento selecionarAssento(Scanner input,Sala sala) {
    try {
        System.out.print("Escolha a fileira (A a T): ");
        char fileiraSelecionada = Character.toUpperCase(input.next().charAt(0));
        input.nextLine();

        System.out.print("Escolha o número da cadeira (1 a 10): ");
        int numeroSelecionado = input.nextInt();
        input.nextLine();

        return sala.getAssento(fileiraSelecionada, numeroSelecionado);
    } catch (Exception e) {
        System.out.println("Entrada inválida. Compra cancelada.");
        input.nextLine();
        return null;
    }
}

    @Override
    public String toString() {
        String numFormatado = String.format("%02d", numero);
        return ocupado ? "[ X ]" : "[ " + numFormatado + " ]";
    }
}
