package school.sptech.loginormyou2up.domain.avaliacao;

import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import javax.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double nota;

    @ManyToOne
    private Usuario avaliador;

    @ManyToOne
    private Usuario avaliado;

    @ManyToOne
    private Treino treino;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }
}
