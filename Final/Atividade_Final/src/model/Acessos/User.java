package model.Acessos;

import java.util.Scanner;
import model.Cinema.Assento;
import model.Cinema.Filme;
import model.Cinema.Ingresso;
import model.Cinema.Sala;
import model.Pessoas.Pessoa;
import model.Sistema;

public class User {
    Scanner input = new Scanner(System.in);
    public void menuUser() {
        int opc;
        do{
            System.out.println("\n=== Menu Usuário ===");
            System.out.println("1. Comprar Ingresso");
            System.out.println("2. Ver mapa de assentos");
            System.out.println("3. Ver Filmes em Cartaz");
            System.out.println("0. Voltar");
            opc = input.nextInt(); input.nextLine();

        switch (opc) {
            case 1 -> comprar();
            case 2 -> verMapaAssentos();
            case 3 -> verFilmesEmCartaz();
            case 0 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida.");
        }
        }while(opc != 0);
    }

    public void verMapaAssentos() {
        System.out.print("Sala (1 a 5): ");
        int salaNum = input.nextInt();
        input.nextLine();

        if (salaNum < 1 || salaNum > 5) {
            System.out.println("Sala inválida.");
            return;
        }
        Sala sala = Sistema.salas[salaNum - 1];
        sala.mostrarMapaAssentos();
    }

    public void verFilmesEmCartaz() {
        System.out.println("\n=== Filmes em Cartaz ===");
        for (Sala sala : Sistema.salas) {
            Filme filme = sala.getFilme();
            if (filme != null) {
                System.out.println("Sala " + sala.getNumeroSala() + ": " + filme.toString());
            } else {
                System.out.println("Sala " + sala.getNumeroSala() + ": Nenhum filme cadastrado.");
            }
        }
    }

    public void comprar() {
        System.out.println("\n=== Compra de Ingressos ===");
        
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Idade: ");
        int idade = input.nextInt();
        input.nextLine(); 

        System.out.print("Categoria (normal, estudante, idoso, professor): ");
        String categoria = input.nextLine().toLowerCase();

        Pessoa pessoa = new Pessoa(nome, idade, categoria);

    
        System.out.print("Sala (1 a 5): ");
        int salaNum = input.nextInt();
        input.nextLine();

        if (salaNum < 1 || salaNum > 5) {
            System.out.println("Sala inválida.");
            return;
        }
        Sala sala = Sistema.salas[salaNum - 1];
        Filme filme = sala.getFilme();

        if (filme == null) {
            System.out.println("Esta sala ainda não possui um filme cadastrado.");
            return;
        }
        sala.mostrarMapaAssentos();
        System.out.print("Escolha a fileira (A a T): ");
        char fileira = Character.toUpperCase(input.next().charAt(0));
        input.nextLine();

        System.out.print("Escolha o número da cadeira (1 a 10): ");
        int numero = input.nextInt();
        input.nextLine();

        if (fileira < 'A' || fileira > 'T' || numero < 1 || numero > 10) {
            System.out.println("Assento inválido.");
            return;
        }

        Assento assento = sala.getAssento(fileira, numero);
        if (assento.isOcupado()) {
            System.out.println("Assento já está ocupado. Tente outro.");
            return;
        }

     
        assento.ocupar();
        double precoBase = filme.getPrecoIngresso();
        double precoFinal = precoBase * (1 - pessoa.getDesconto());
        Ingresso ingresso = new Ingresso(pessoa, sala, assento, precoFinal);
        Sistema.ingressosVendidos.add(ingresso);

 
        System.out.println("\n=== Ingresso Comprado ===");
        System.out.println(ingresso);

    }
}
