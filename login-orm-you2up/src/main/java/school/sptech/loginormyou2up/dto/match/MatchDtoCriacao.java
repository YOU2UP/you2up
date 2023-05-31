package school.sptech.loginormyou2up.dto.match;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

public class MatchDtoCriacao {

    @Schema(description = "Usuário 1", example = "1")
    private Usuario usuario1;

    @Schema(description = "Usuário 2", example = "2")
    private Usuario usuario2;

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
}
