package model.Pessoa;

import java.util.Scanner;

public class Pessoa {

    private String nome;
    private int idade;
    private String categoria;

    public Pessoa(String nome, int idade, String categoria) {
        this.nome = nome;
        this.idade = idade;
        this.categoria = categoria.toLowerCase();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.toLowerCase();
    }

    public double calcularPrecoFinal(double precoBase) {
        return precoBase * (1 - getDesconto());
    }

    public double getDesconto() {
        return switch (categoria) {
            case "estudante" ->
                0.5;
            case "idoso" ->
                1.0;
            case "professor" ->
                0.3;
            default ->
                0.0;
        };
    }

    public static Pessoa criarPessoa(Scanner input) throws Exception {
        try {
            System.out.print("Nome: ");
            String nome = input.nextLine();
            System.out.print("Idade: ");
            int idade = input.nextInt();
            input.nextLine();
            System.out.print("Categoria (normal, estudante, idoso, professor): ");
            String cat = input.nextLine().toLowerCase();
            return new Pessoa(nome, idade, cat);
        }
        catch(Exception e){
            return null;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

}
