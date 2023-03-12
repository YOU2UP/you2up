package school.sptech.apitreinoyou2up;

public class TreinoArLivre extends Treino{

    private String clima; //nublado, chuvoso, ensolarado

    public TreinoArLivre(int codigo, String local, int duracao, String intensidade, String clima) {
        super(codigo, local, duracao, intensidade);
        this.clima = clima;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
