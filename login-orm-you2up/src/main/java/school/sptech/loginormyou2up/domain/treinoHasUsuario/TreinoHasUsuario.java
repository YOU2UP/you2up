package school.sptech.loginormyou2up.domain.treinoHasUsuario;

import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

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

    public TreinoHasUsuario(TreinoHasUsuarioId treinoHasUsuarioId, Treino treino, Usuario usuario, LocalDateTime inicioTreino, Integer quantidadeTreinos, boolean isRealizado) {
        this.treinoHasUsuarioId = treinoHasUsuarioId;
        this.treino = treino;
        this.usuario = usuario;
        this.inicioTreino = inicioTreino;
    }

    public TreinoHasUsuario() {
    }

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

    public TreinoHasUsuarioId getTreinoHasUsuarioId() {
        return treinoHasUsuarioId;
    }

    public void setTreinoHasUsuarioId(TreinoHasUsuarioId treinoHasUsuarioId) {
        this.treinoHasUsuarioId = treinoHasUsuarioId;
    }
}
