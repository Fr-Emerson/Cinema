package model.Cinema;

import model.Pessoa.Pessoa;

public class Ingresso {
    private final Pessoa pessoa;
    private final Sala sala;
    private final Assento assento;
    private final double valorPago;

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
    public void comprarIngresso() {
        if (assento.isOcupado()) {
            System.out.println("Assento já ocupado. Escolha outro assento.");
        } else {
            assento.setOcupado(true);
            System.out.println("Ingresso comprado com sucesso!");
            
        }
    }
    @Override
    public String toString() {
        return "Ingresso{" +
                "pessoa=" + pessoa.getNome() +
                "Filme" + sala.getFilme().getTitulo() +
                ", sala=" + sala.getNumeroSala() +
                ", assento=" + assento.getNumero() +
                ", valorPago=" + valorPago +
                '}';
    }
}
