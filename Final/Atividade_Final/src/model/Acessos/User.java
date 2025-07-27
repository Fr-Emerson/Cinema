package model.Acessos;

import java.util.Scanner;
import model.Cinema.*;
import model.Pessoa.Pessoa;
import model.Sistema;
import model.utilidades;

public class User {

    Scanner input = new Scanner(System.in);

    public void menuUser() {
        int opc;
        do {
            opc = -1;

            utilidades.ConsoleUtil.pause();
            utilidades.ConsoleUtil.clearScreen();

            try {
                System.out.println("\n=== Menu Usuário ===");
                System.out.println("1. Comprar Ingresso");
                System.out.println("2. Ver mapa de assentos");
                System.out.println("3. Ver Filmes em Cartaz");
                System.out.println("0. Voltar");
                System.out.print("Escolha >>> ");
                opc = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
            }
            switch (opc) {
                case 1 ->
                    comprar();
                case 2 ->
                    verMapaAssentos();
                case 3 ->
                    verFilmesEmCartaz();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);
    }

    public void verMapaAssentos() {
        try {
            System.out.print("Sala (1 a 5): ");
            int salaNum = input.nextInt();
            input.nextLine();

            if (salaNum < 1 || salaNum > 5) {
                System.out.println("Sala inválida.");
                return;
            }
            Sala sala = Sistema.salas[salaNum - 1];
            sala.mostrarMapaAssentos();
        } catch (Exception e) {
            System.out.println("Erro ao exibir mapa de assentos.");
        }

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

        Pessoa pessoa;
        try {
            pessoa = Pessoa.criarPessoa(input);
        } catch (Exception e) {
            System.out.println("Erro ao criar pessoa: " + e.getMessage());
            return;
        }
        if (pessoa == null) {
            return;
        }

        Sala sala = new Sala();
        sala = sala.selecionarSala(input);
        if (sala == null || sala.getFilme() == null) {
            System.out.println("Sala inválida ou sem filme.");
            return;
        }

        sala.mostrarMapaAssentos();
        System.out.println("Filme: " + sala.getFilme());

        Assento assento = new Assento().selecionarAssento(input, sala);
        if (assento == null) {
            return;
        }

        if (assento.isOcupado()) {
            System.out.println("Assento já ocupado.");
            return;
        }

        assento.setOcupado(true);
        double precoFinal = pessoa.calcularPrecoFinal(sala.getFilme().getPrecoIngresso());

        Ingresso ingresso = new Ingresso(pessoa, sala, assento, precoFinal);
        Sistema.ingressosVendidos.add(ingresso);

        System.out.println("\n=== Ingresso Comprado ===");
        System.out.println(ingresso);
    }

}
