package sptech.school.apitreinoyou2up.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public abstract class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String local;
    @Min(10)
    private int duracao; // em minutos
    private String intensidade; // baixa, média, alta

    public Treino(int codigo, String local, int duracao, String intensidade) {
        this.codigo = codigo;
        this.local = local;
        this.duracao = duracao;
        this.intensidade = intensidade;
    }

    public Treino() {
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
