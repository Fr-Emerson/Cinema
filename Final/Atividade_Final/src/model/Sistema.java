package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import model.Acessos.Admin;
import model.Acessos.User;
import model.Cinema.Filme;
import model.Cinema.Ingresso;
import model.Cinema.Sala;

public class Sistema {

    Scanner input = new Scanner(System.in);
    public static ArrayList<Ingresso> ingressosVendidos = new ArrayList<>();
    User usuario;
    Admin admin;
    public static Sala[] salas = new Sala[5];

    public Sistema() {
        usuario = new User();
        admin = new Admin();
        for (int i = 0; i < salas.length; i++) {
            salas[i] = new Sala(i + 1);
        }
    }

    public static void verFilmesEmCartaz() {
        System.out.println("\n=== Filmes em Cartaz ===");
        for (Sala sala : salas) {
            Filme filme = sala.getFilme();
            if (filme != null) {
                System.out.println("Sala " + sala.getNumeroSala() + ": " + filme.toString());
            } else {
                System.out.println("Sala " + sala.getNumeroSala() + ": Nenhum filme cadastrado.");
            }
        }
    }

    public static List<Filme> getFilmesEmCartaz() {
        Set<Filme> filmes = new HashSet<>();
        for (Sala sala : salas) {
            if (sala.getFilme() != null) {
                filmes.add(sala.getFilme());
            }
        }
        return new ArrayList<>(filmes);
    }

    public static List<Filme> getFilmesOrdenadosPorLucro() {
        Map<Filme, Double> lucro = calcularLucroPorFilme();
        List<Filme> filmes = getFilmesEmCartaz();

        filmes.sort((f1, f2)
                -> Double.compare(lucro.getOrDefault(f2, 0.0), lucro.getOrDefault(f1, 0.0))
        );

        return filmes;
    }

    public static Map<Filme, Double> calcularLucroPorFilme() {
        Map<Filme, Double> lucro = new HashMap<>();

        for (Ingresso ingresso : ingressosVendidos) {
            Filme filme = ingresso.getSala().getFilme();
            if (filme != null) {
                lucro.put(filme, lucro.getOrDefault(filme, 0.0) + ingresso.getValorPago());
            }
        }

        return lucro;
    }

    public void menu() {
        int opcao;
        System.out.println("Bem-vindo ao Cinema IFMA!");
        do {
            try {
                utilidades.ConsoleUtil.pause();
                utilidades.ConsoleUtil.clearScreen();
                System.out.println("\nMenu do Sistema:");
                System.out.println("1. Acessar como Usuário");
                System.out.println("2. Acessar como Administrador");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                opcao = input.nextInt();
                input.nextLine();

            } catch (Exception e) {
                input.nextLine();
                opcao = -1;
            }
            switch (opcao) {
                case 1 ->
                    usuario.menuUser();
                case 2 ->
                    admin.menuAdmin();
                case 0 ->
                    System.out.println("Saindo...");
                default -> {
                    System.out.println("Opção inválida.");
                    menu();
                }
            }
        } while (opcao != 0);
    }
}
