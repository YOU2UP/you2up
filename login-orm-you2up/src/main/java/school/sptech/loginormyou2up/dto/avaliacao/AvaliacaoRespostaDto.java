package school.sptech.loginormyou2up.dto.avaliacao;

import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRetornoDetalhes;
import school.sptech.loginormyou2up.dto.usuario.UsuarioResumoDto;

public class AvaliacaoRespostaDto {
    private Integer id;
    private double nota;
    private UsuarioDtoRetornoDetalhes avaliador;
    private UsuarioDtoRetornoDetalhes avaliado;
    private TreinoDtoJsonUsuario treino;

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

    public UsuarioDtoRetornoDetalhes getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(UsuarioDtoRetornoDetalhes avaliador) {
        this.avaliador = avaliador;
    }

    public UsuarioDtoRetornoDetalhes getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(UsuarioDtoRetornoDetalhes avaliado) {
        this.avaliado = avaliado;
    }

    public TreinoDtoJsonUsuario getTreino() {
        return treino;
    }

    public void setTreino(TreinoDtoJsonUsuario treino) {
        this.treino = treino;
    }
}
