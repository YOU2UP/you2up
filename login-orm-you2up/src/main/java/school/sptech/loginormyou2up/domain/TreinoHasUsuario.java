package school.sptech.loginormyou2up.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TreinoHasUsuario  {

    @EmbeddedId
    private TreinoHasUsuarioId treinoHasUsuarioId;

    @MapsId("treinoId")
    @ManyToOne
    private Treino treino;

    @MapsId("usuarioId")
    @ManyToOne
    private Usuario usuario;

    private LocalDateTime inicioTreino;
    private Integer quantidadeTreinos;
    private boolean isRealizado;

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getInicioTreino() {
        return inicioTreino;
    }

    public void setInicioTreino(LocalDateTime inicioTreino) {
        this.inicioTreino = inicioTreino;
    }

    public Integer getQuantidadeTreinos() {
        return quantidadeTreinos;
    }

    public void setQuantidadeTreinos(Integer quantidadeTreinos) {
        this.quantidadeTreinos = quantidadeTreinos;
    }

    public boolean isRealizado() {
        return isRealizado;
    }

    public void setRealizado(boolean realizado) {
        isRealizado = realizado;
    }

    public TreinoHasUsuarioId getTreinoHasUsuarioId() {
        return treinoHasUsuarioId;
    }

    public void setTreinoHasUsuarioId(TreinoHasUsuarioId treinoHasUsuarioId) {
        this.treinoHasUsuarioId = treinoHasUsuarioId;
    }
}
