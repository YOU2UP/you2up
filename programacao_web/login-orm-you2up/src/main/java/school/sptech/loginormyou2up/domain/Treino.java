package school.sptech.loginormyou2up.domain;

import org.aspectj.weaver.ast.Not;
import school.sptech.loginormyou2up.service.observer.Observado;
import school.sptech.loginormyou2up.service.observer.Observador;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Treino implements Observado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String periodo;

    private String preferencia;

    private LocalDateTime inicioTreino;

    @OneToMany(mappedBy = "treino")
    private List<Usuario> usuarios;


    @Override
    public void notificarUsuario() {
        for (Observador u: usuarios) {
            u.notificarTreino(this);
        }
    }

    public LocalDateTime getInicioTreino() {
        return inicioTreino;
    }

    public void setInicioTreino(LocalDateTime inicioTreino) {
        this.inicioTreino = inicioTreino;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }


}
