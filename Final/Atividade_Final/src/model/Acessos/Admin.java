package model.Acessos;

import java.util.Scanner;
import model.Cinema.Filme;
import model.Cinema.Ingresso;
import model.Sistema;

public class Admin {

    Scanner input = new Scanner(System.in);

    public void menuAdmin() {
        int opc;
        do {
            System.out.println("\n=== Menu Administrador ===");
            System.out.println("1. Cadastrar Filme em Sala");
            System.out.println("2. remover Filme de Sala");
            System.out.println("3. Relatório de Lucro");
            System.out.println("0. Voltar");
            opc = input.nextInt();
            input.nextLine();

            switch (opc) {
                case 1 ->
                    cadastrarFilme();
                case 2 ->
                    verOcupacao();
                case 3 ->
                    gerarRelatorioLucro();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);
    }

    private void cadastrarFilme() {
        System.out.print("Título: ");
        String titulo = input.nextLine();
        System.out.print("Duração (min): ");
        int duracao = input.nextInt();
        input.nextLine();
        System.out.print("Gênero: ");
        String genero = input.nextLine();
        System.out.print("Preço do ingresso (R$): ");
        double preco = input.nextDouble();
        input.nextLine();
        System.out.print("Sala (1 a 5): ");
        int sala = input.nextInt();
        input.nextLine();

        Filme filme = new Filme(titulo, duracao, genero, preco);
        Sistema.salas[sala - 1].setFilme(filme);
        System.out.println("Filme cadastrado com sucesso.");
    }

    private void verOcupacao() {
        System.out.print("Sala (1 a 5): ");
        int sala = input.nextInt();
        input.nextLine();
        Sistema.salas[sala - 1].mostrarMapaAssentos();
    }

    private void gerarRelatorioLucro() {
        double[] lucros = new double[5]; 
        for (Ingresso ingresso : Sistema.ingressosVendidos) {
            int salaIndex = ingresso.getSala().getNumeroSala() - 1;
            lucros[salaIndex] += ingresso.getValorPago();
        }
        System.out.println("\n=== Relatório de Lucro por Sala ===");
        for (int i = 0; i < lucros.length; i++) {
            System.out.printf("Sala %d: R$ %.2f%n", (i + 1), lucros[i]);
        }
    }
}
