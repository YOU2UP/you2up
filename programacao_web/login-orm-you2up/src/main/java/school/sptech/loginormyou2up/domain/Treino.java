package school.sptech.loginormyou2up.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Treino {
    @Id
    private Integer id;

    private String periodo;

    private String preferencia;

    private int peso;

    private String estagio; // iniciante, intermediário, avançado

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }
}
