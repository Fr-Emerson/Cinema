package model.Cinema;

public class Filme {
    private final String titulo;
    private final int duracao;
    private final String genero;
    private final double precoIngresso;

    public Filme(String titulo, int duracao, String genero, double precoIngresso) {
        this.titulo = titulo == null ? "Indefinido" : titulo;
        this.duracao = duracao < 0 ? 0 : duracao; 
        this.genero = genero != null ? genero : "Indefinido";
        this.precoIngresso = precoIngresso < 0 ? 0.0 : precoIngresso; 
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }
    
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + duracao + "min) - Ingresso: R$ " + String.format("%.2f", precoIngresso);
    }
}
