package school.sptech.loginormyou2up.dto.treino;

public class QuantidadeTreinosPorDiaSemanaDto {
    private int segunda = 0;
    private int terca= 0;
    private int quarta= 0;
    private int quinta= 0;
    private int sexta = 0;
    private int sabado= 0;
    private int domingo= 0;

    public void incrementaSegunda(){
        segunda++;
    }

    public void incrementaTerca(){
        terca++;
    }

    public void incrementaQuarta(){
        quarta++;
    }

    public void incrementaQuinta(){
        quinta++;
    }

    public void incrementaSexta(){
        sexta++;
    }

    public void incrementaSabado(){
        sabado++;
    }

    public void incrementaDomingo(){
        domingo++;
    }

    public int getSegunda() {
        return segunda;
    }

    public int getTerca() {
        return terca;
    }

    public int getQuarta() {
        return quarta;
    }

    public int getQuinta() {
        return quinta;
    }

    public int getSexta() {
        return sexta;
    }

    public int getSabado() {
        return sabado;
    }

    public int getDomingo() {
        return domingo;
    }
}
