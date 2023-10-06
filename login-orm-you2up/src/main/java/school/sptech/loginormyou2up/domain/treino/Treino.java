package school.sptech.loginormyou2up.domain.treino;


import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;

import javax.persistence.*;
import java.util.List;

@Entity
public class Treino{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTreino;

    private String periodo;

    @OneToMany(mappedBy = "treino", orphanRemoval = true)
    private List<TreinoHasUsuario> usuarios;

    private boolean isRealizado;

    public Treino(Integer idTreino, String periodo, List<TreinoHasUsuario> usuarios) {
        this.idTreino = idTreino;
        this.periodo = periodo;
        this.usuarios = usuarios;
    }

    public Treino() {
    }

    public Integer getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<TreinoHasUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<TreinoHasUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isRealizado() {
        return isRealizado;
    }

    public void setRealizado(boolean realizado) {
        isRealizado = realizado;
    }
}
