package school.sptech.loginormyou2up.domain.foto;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;


@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFoto;
    private String url;
    @ManyToOne
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
