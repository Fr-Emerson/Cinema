package model.Cinema;

public class Assento {
    private char fileira;
    private int numero;
    private boolean ocupado;

    public Assento(char fileira, int numero) {
        this.fileira = fileira;
        this.numero = numero;
        this.ocupado = false;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public char getFileira() {
        return fileira;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        String numFormatado = String.format("%02d", numero);
        return ocupado ? "[ X ]" : "[ " + numFormatado + " ]";
    }
}
