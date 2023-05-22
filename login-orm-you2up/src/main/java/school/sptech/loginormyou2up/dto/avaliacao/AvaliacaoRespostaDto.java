package school.sptech.loginormyou2up.dto.avaliacao;

import school.sptech.loginormyou2up.dto.usuario.UsuarioResumoDto;

public class AvaliacaoRespostaDto {
    private Integer id;
    private double nota;
    private UsuarioResumoDto avaliador;
    private UsuarioResumoDto avaliado;

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

    public UsuarioResumoDto getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(UsuarioResumoDto avaliador) {
        this.avaliador = avaliador;
    }

    public UsuarioResumoDto getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(UsuarioResumoDto avaliado) {
        this.avaliado = avaliado;
    }
}
