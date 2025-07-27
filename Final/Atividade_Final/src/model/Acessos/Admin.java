package model.Acessos;

import java.util.Scanner;
import model.Cinema.Filme;
import model.Cinema.Ingresso;
import model.Sistema;
import model.utilidades;

public class Admin {

    Scanner input = new Scanner(System.in);

    public void menuAdmin() {
        int opc;
        do {
            opc = -1;
            utilidades.ConsoleUtil.pause();
            utilidades.ConsoleUtil.clearScreen();
            System.out.println("\n=== Menu Administrador ===");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Remover Filme");
            System.out.println("3. Ver Ocupação da Sala");
            System.out.println("4. Gerar Relatório de Lucro");
            System.out.println("0. Voltar");
            try {
                opc = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.nextLine();
            }
            finally{
                switch (opc) {
                case 1 -> cadastrarFilme();
                case 2 -> removerFilme();
                case 3 -> verOcupacao();
                case 4 -> gerarRelatorioLucro();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida.");
            }
        }

    } while (opc != 0);
    }
    private void cadastrarFilme() {
        try {
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
            if (sala < 1 || sala > 5) {
                System.out.println("Sala inválida.");
                return;
            }
            input.nextLine();
    
            Filme filme = new Filme(titulo, duracao, genero, preco);
            Sistema.salas[sala - 1].cadastrarFilme(filme);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar filme: " + e.getMessage());
        }
    }

    private void verOcupacao() {
        try {
            System.out.print("Sala (1 a 5): ");
            int sala = input.nextInt();
            input.nextLine();
            if(sala < 1 || sala > 5){
                System.out.println("Essa sala não existe");
            }
            else{
                Sistema.salas[sala - 1].mostrarMapaAssentos();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver ocupação: " + e.getMessage());
        }
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
    public void removerFilme()
   {
       try {
           System.out.print("Nome do filme a ser removido: ");
           String nomeFilme = input.nextLine();
           System.out.print("Sala (1 a 5): ");
           int sala = input.nextInt();
           input.nextLine();
           if (sala < 1 || sala > 5) {
               System.out.println("Essa sala não existe");
           } else {
               Sistema.salas[sala - 1].removerFilme(nomeFilme);
           }
           Sistema.salas[sala - 1].removerFilme(nomeFilme);
       } catch (Exception e) {
           System.out.println("Erro ao remover filme: " + e.getMessage());
       }
   }
}