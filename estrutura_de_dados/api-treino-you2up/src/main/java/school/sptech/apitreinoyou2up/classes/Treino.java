package school.sptech.apitreinoyou2up.classes;

public abstract class Treino {

    private int codigo;
    private String local;
    private int duracao; // em minutos
    private String intensidade; // baixa, média, alta


    public Treino() {
    }

    public Treino(int codigo, String local, int duracao, String intensidade) {
        this.codigo = codigo;
        this.local = local;
        this.duracao = duracao;
        this.intensidade = intensidade;
    }


    public abstract double getGastoCalorico();

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
