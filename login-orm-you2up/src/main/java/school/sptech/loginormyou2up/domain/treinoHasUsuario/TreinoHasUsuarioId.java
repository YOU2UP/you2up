package school.sptech.loginormyou2up.domain.treinoHasUsuario;

import school.sptech.loginormyou2up.service.usuario.UsuarioService;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TreinoHasUsuarioId implements Serializable {
    private Integer treinoId;
    private Integer usuarioId;

    public Integer getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(Integer treinoId) {
        this.treinoId = treinoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
