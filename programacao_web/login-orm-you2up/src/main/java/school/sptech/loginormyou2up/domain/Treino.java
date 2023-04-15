package school.sptech.loginormyou2up.domain;

import school.sptech.loginormyou2up.models.observer.Observado;
import school.sptech.loginormyou2up.models.observer.Observador;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Treino implements Observado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String periodo;

    private String preferencia;

    private int peso;

    private String estagio; // iniciante, intermediário, avançado

    //private String[] usuarios = {"user1", "user2"};
    private List<Observador> usuarios = new ArrayList<>();

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

    public List<Observador> getUsuarios() {
        return usuarios;
    }

//    @Override
//    public void notificarUsuario(Treino treino) {
//        Usuario usuario = new Usuario();
//        for (int i = 0; i < usuarios.length; i++) {
//            usuario.notificarTreino(treino);
//        }
//    }

    @Override
    public void notificarUsuario(Treino treino) {
        for (Observador usuario: usuarios) {
            usuario.notificarTreino(treino);
        }
    }

    @Override
    public String toString() {
        return "Treino{" +
                "id=" + id +
                ", periodo='" + periodo + '\'' +
                ", preferencia='" + preferencia + '\'' +
                ", peso=" + peso +
                ", estagio='" + estagio + '\'' +
                '}';
    }
}
