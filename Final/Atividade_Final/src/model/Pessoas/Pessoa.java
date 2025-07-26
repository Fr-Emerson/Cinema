package model.Pessoas;

public class Pessoa {
    private String nome;
    private int idade;
    private String categoria; // normal, estudante, idoso, professor

    public Pessoa(String nome, int idade, String categoria) {
        this.nome = nome;
        this.idade = idade;
        this.categoria = categoria.toLowerCase();
    }

    public String getCategoria() {
        return categoria;
    }

    public double getDesconto() {
        return switch (categoria) {
            case "estudante" -> 0.5;
            case "idoso" -> 1.0;
            case "professor" -> 0.3;
            default -> 0.0;
        };
    }

    public String getNome() {
        return nome;
    }
}
