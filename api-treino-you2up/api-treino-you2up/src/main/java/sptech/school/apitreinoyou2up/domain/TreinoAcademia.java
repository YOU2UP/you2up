package sptech.school.apitreinoyou2up.domain;

import jakarta.persistence.Entity;

@Entity
public class TreinoAcademia extends Treino {

    private String tipo; // A, B ou C

    /* A = Ombros, peito e tríceps;
     B = Bíceps, costas e abdômen;
     C: Treino de pernas completo.*/

    public TreinoAcademia(int codigo, String local, int duracao, String intensidade, String tipo) {
        super(codigo, local, duracao, intensidade);
        this.tipo = tipo;
    }

    public TreinoAcademia() {
    }

    @Override
    public double getGastoCalorico() {
        double met = 0;
        double peso = 70.0; // valor ilustrativo do peso humano para realizar o cálculo

        if(super.getIntensidade().equalsIgnoreCase("baixa")){
            met =  3.5;
        } else if (super.getIntensidade().equalsIgnoreCase("media")) {
            met = 5.0;
        }
        else if (super.getIntensidade().equalsIgnoreCase("alta")){
            met = 6.0;
        }

        return met * peso * (super.getDuracao() / 60);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
