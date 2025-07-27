package model.Cine;

import model.Pessoa.Pessoa;

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

    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


    public void setSala(Sala sala) {
        this.sala = sala;
    }


    public void setAssento(Assento assento) {
        this.assento = assento;
    }


    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }


    public Sala getSala() {
        return sala;
    }

    public double getValorPago() {
        return valorPago;
    }

    
    public Pessoa getPessoa() {
        return pessoa;
    }
    public Assento getAssento() {
        return assento;
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
        return """
               Ingresso Comprado:
               Pessoa: """ + this.pessoa.getNome() + "\n" +
               "Sala: " + this.sala + "\n" +
               "Assento: " + this.assento.mostrarAssento() + "\n" +
               "Valor Pago: R$ " + this.valorPago;
    }
}