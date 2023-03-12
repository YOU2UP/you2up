package school.sptech.apitreinoyou2up;

import java.util.List;

public class TreinoAcademia extends Treino{

    private String tipo; // A, B ou C

    /* A = Ombros, peito e tríceps;
     B = Bíceps, costas e abdômen;
     C: Treino de pernas completo.*/

    public TreinoAcademia(int codigo, String local, int duracao, String intensidade, String maquinario) {
        super(codigo, local, duracao, intensidade);
        this.tipo = maquinario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
