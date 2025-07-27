package model;

import java.util.ArrayList;
import java.util.Scanner;
import model.Acessos.Admin;
import model.Acessos.User;
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
