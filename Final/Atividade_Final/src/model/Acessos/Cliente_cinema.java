package model.Acessos;

import java.util.Scanner;
import model.Cine.Assento;
import model.Cine.Ingresso;
import model.Cine.Sala;
import model.Cinema;
import model.Pessoa.Pessoa;
import model.utilidades;

public class Cliente_cinema {

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
                System.out.println("4. Ver Ingressos Comprados");
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
                    Cinema.verFilmesEmCartaz();
                case 4 ->
                    verHistoricoCompras();
                case 0 ->
                    System.out.println("Voltando ao menu principal...");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);
    }

    public void verHistoricoCompras() {
        System.out.println("Digite o nome do cliente:");
        String nome = input.nextLine();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        boolean encontrou = false;
        for (Ingresso ingresso : Cinema.ingressosVendidos) {
            if (ingresso.getPessoa().getNome().equalsIgnoreCase(nome)) {
                System.out.println(ingresso);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum ingresso encontrado para: " + nome);
        }
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
            Sala sala = Cinema.salas[salaNum - 1];
            sala.mostrarMapaAssentos();
        } catch (Exception e) {
            System.out.println("Erro ao exibir mapa de assentos.");
        }

    }

    public void comprar() {
        System.out.println("\n=== Compra de Ingressos ===");
        if(!Cinema.haFilmesEmCartaz()){
            System.out.println("Nenhum filme em cartaz.");
            return;
        }
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
        Cinema.ingressosVendidos.add(ingresso);

        System.out.println("\n=== Ingresso Comprado ===");
        System.out.println(ingresso);
    }
}