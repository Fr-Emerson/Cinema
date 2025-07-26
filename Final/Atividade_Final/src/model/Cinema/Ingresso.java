package model.Cinema;

import model.Pessoas.Pessoa;

public class Ingresso {
    private Pessoa pessoa;
    private Sala sala;
    private Assento assento;
    private double valorPago;

    public Ingresso(Pessoa pessoa, Sala sala, Assento assento, double valorPago) {
        this.pessoa = pessoa;
        this.sala = sala;
        this.assento = assento;
        this.valorPago = valorPago;
    }

    public Sala getSala() {
        return sala;
    }

    public double getValorPago() {
        return valorPago;
    }
    @Override
    public String toString() {
        return "Ingresso{" +
                "pessoa=" + pessoa.getNome() +
                ", sala=" + sala.getNumeroSala() +
                ", assento=" + assento.getNumero() +
                ", valorPago=" + valorPago +
                '}';
    }
}
