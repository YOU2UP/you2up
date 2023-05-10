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

    @OneToMany(mappedBy = "treino", orphanRemoval = true)
    private List<TreinoHasUsuario> usuarios;


    @Override
    public void notificarUsuario() {
        return;

     /*   for (Observador u : usuarios) {
            u.notificarTreino(this);
        }*/
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

    public List<TreinoHasUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<TreinoHasUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
