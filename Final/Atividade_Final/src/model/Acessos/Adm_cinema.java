package model.Acessos;

import java.util.List;
import java.util.Scanner;
import model.Cine.Filme;
import model.Cine.Ingresso;
import model.Cine.Sala;
import model.Cinema;
import model.utilidades;

public class Adm_cinema {

    Scanner input = new Scanner(System.in);

    public void menuAdmin() {
        int opc;
        do {
            opc = -1;
            utilidades.ConsoleUtil.pause();
            utilidades.ConsoleUtil.clearScreen();
            System.out.println("\n=== Menu Administrador ===");
            System.out.println("1. Ver filmes em cartaz");
            System.out.println("2. Cadastrar Filme");
            System.out.println("3. Remover Filme");
            System.out.println("4. Ver Ocupação da Sala");
            System.out.println("5. Gerar Relatório de Lucro");
            System.out.println("6. Preencher Salas com Filmes Mais Lucrativos");
            System.out.println("0. Voltar");
            try {
                opc = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.nextLine();
            } finally {
                switch (opc) {
                    case 1 ->
                        Cinema.verFilmesEmCartaz();
                    case 2 ->
                        cadastrarFilme();
                    case 3 ->
                        removerFilme();
                    case 4 ->
                        verOcupacao();
                    case 5 ->
                        gerarRelatorioLucro();
                    case 6 ->
                        preencherSalas();
                    case 0 ->
                        System.out.println("Voltando ao menu principal...");
                    default ->
                        System.out.println("Opção inválida.");
                }
            }

        } while (opc != 0);
    }

    private void cadastrarFilme() {
        System.out.println("\n\n\n=== Cadastro de Filme ===");
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
            Cinema.salas[sala - 1].cadastrarFilme(filme);

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar filme: " + e.getMessage());
        }
    }

    private void verOcupacao() {
        try {
            System.out.print("Sala (1 a 5): ");
            int sala = input.nextInt();
            input.nextLine();
            if (sala < 1 || sala > 5) {
                System.out.println("Essa sala não existe");
            } else {
                Cinema.salas[sala - 1].mostrarMapaAssentos();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ver ocupação: " + e.getMessage());
        }
    }

    private void gerarRelatorioLucro() {
        double[] lucros = new double[5];
        for (Ingresso ingresso : Cinema.ingressosVendidos) {
            int salaIndex = ingresso.getSala().getNumeroSala() - 1;
            lucros[salaIndex] += ingresso.getValorPago();
        }
        System.out.println("\n=== Relatório de Lucro por Sala ===");
        for (int i = 0; i < lucros.length; i++) {
            System.out.printf("Sala %d: R$ %.2f%n", (i + 1), lucros[i]);
        }
    }

    public void removerFilme() {
        if(!Cinema.haFilmesEmCartaz()){
            System.out.println("Nenhum filme para remover.");
            return;
        }
        try {
            System.out.print("Nome do filme a ser removido: ");
            String nomeFilme = input.nextLine();
            System.out.print("Sala (1 a 5): ");
            int sala = input.nextInt();
            input.nextLine();
            if (sala < 1 || sala > 5) {
                System.out.println("Essa sala não existe");
            } else {
                Cinema.salas[sala - 1].removerFilme(nomeFilme);
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover filme: " + e.getMessage());
        }
    }

    private void preencherSalas() {
        List<Filme> filmesOrdenados = Cinema.getFilmesOrdenadosPorLucro();

        if (filmesOrdenados.isEmpty()) {
            System.out.println("Nenhum filme em cartaz para preencher salas.");
            return;
        }

        int index = 0, count = 0;

        for (Sala sala : Cinema.salas) {
            if (sala.getFilme() == null) {
                Filme escolhido = filmesOrdenados.get(index);
                sala.setFilme(escolhido);
                System.out.printf("Sala %d preenchida com: %s%n", sala.getNumeroSala(), escolhido.toString());
                index = (index + 1) % filmesOrdenados.size();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Todas as salas já estão ocupadas.");
        } else {
            System.out.printf("%d sala(s) foram preenchidas com os filmes mais lucrativos.%n", count);
        }
    }
}
