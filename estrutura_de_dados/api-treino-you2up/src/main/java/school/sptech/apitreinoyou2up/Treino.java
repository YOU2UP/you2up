package school.sptech.apitreinoyou2up;

public abstract class Treino {

    private int codigo;
    private String local;
    private int duracao; // em minutos
    private String intensidade; // baixa, média, forte


    public Treino() {
    }

    public Treino(int codigo, String local, int duracao, String intensidade) {
        this.codigo = codigo;
        this.local = local;
        this.duracao = duracao;
        this.intensidade = intensidade;
    }


    public double getGastoCalorico(){
        double met = 0;
        double peso = 70.0; // valor ilustrativo do peso humano para realizar o cálculo

        if(intensidade.equalsIgnoreCase("baixa")){
            met =  3.5;
        } else if (intensidade.equalsIgnoreCase("media")) {
            met = 5.0;
        }
        else if (intensidade.equalsIgnoreCase("alta")){
            met = 6.0;
        }

        return met * peso * (duracao / 60);
    };

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }
}
