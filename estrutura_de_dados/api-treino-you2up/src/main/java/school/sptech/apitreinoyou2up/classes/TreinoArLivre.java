package school.sptech.apitreinoyou2up.classes;

public class TreinoArLivre extends Treino{

    private String clima; //nublado, chuvoso, ensolarado

    public TreinoArLivre(int codigo, String local, int duracao, String intensidade, String clima) {
        super(codigo, local, duracao, intensidade);
        this.clima = clima;
    }

    public double getGastoCalorico() {
        double met = 0;
        double peso = 70.0; // valor ilustrativo do peso humano para realizar o cálculo

        if(super.getIntensidade().equalsIgnoreCase("baixa")){
            met = 6.0;
        }
        else if (super.getIntensidade().equalsIgnoreCase("media")) {
            met = 8.3;
        }
        else if (super.getIntensidade().equalsIgnoreCase("alta")){
            met = 10.5;
        }

        return met * peso * (super.getDuracao() / 60);
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }
}
