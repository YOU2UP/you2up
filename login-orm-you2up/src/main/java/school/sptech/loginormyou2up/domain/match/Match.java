package school.sptech.loginormyou2up.domain.match;

import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataMatch;
    private boolean isAtivo;
    @ManyToOne
    private Usuario usuario1;
    @ManyToOne
    private Usuario usuario2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(LocalDateTime dataMatch) {
        this.dataMatch = dataMatch;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        isAtivo = ativo;
    }

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
