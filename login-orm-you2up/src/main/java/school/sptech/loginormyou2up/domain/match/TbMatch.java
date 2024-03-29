package school.sptech.loginormyou2up.domain.match;

import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TbMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMatch;
    @ManyToOne
    private Usuario usuario1;
    @ManyToOne
    private Usuario usuario2;

    private LocalDate dataMatch;

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
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

    public LocalDate getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(LocalDate dataMatch) {
        this.dataMatch = dataMatch;
    }
}
