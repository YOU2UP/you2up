package school.sptech.loginormyou2up.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Treino{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String periodo;

    @OneToMany(mappedBy = "treino", orphanRemoval = true)
    private List<TreinoHasUsuario> usuarios;

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
