package school.sptech.loginormyou2up.domain.foto;

import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;

@Entity
public class FotoPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFotoPerfil;
    private String url;
    @OneToOne
    private Usuario usuario;

    public int getIdFotoPerfil() {
        return idFotoPerfil;
    }

    public void setIdFotoPerfil(int idFotoPerfil) {
        this.idFotoPerfil = idFotoPerfil;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
