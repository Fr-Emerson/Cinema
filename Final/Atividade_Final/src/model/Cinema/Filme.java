package model.Cinema;

public class Filme {
    private String titulo;
    private int duracao;
    private String genero;
    private double precoIngresso;

    public Filme(String titulo, int duracao, String genero, double precoIngresso) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.genero = genero;
        this.precoIngresso = precoIngresso;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + duracao + "min) - Ingresso: R$ " + String.format("%.2f", precoIngresso);
    }
}
