package school.sptech.loginormyou2up.dto;

import school.sptech.loginormyou2up.dto.usuario.UsuarioResumoDto;

public class FotoRespostaDto {

    private int idFoto;
    private String url;
    private UsuarioResumoDto usuario;

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UsuarioResumoDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResumoDto usuario) {
        this.usuario = usuario;
    }
}
