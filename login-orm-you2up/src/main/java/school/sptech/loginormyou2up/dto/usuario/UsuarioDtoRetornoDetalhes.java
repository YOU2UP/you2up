package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;

import java.time.LocalDate;

public class UsuarioDtoRetornoDetalhes {

    //Essa DTO retorna algumas informações a mais para os endpoints

    private int id;

    @Schema(description = "Nome do usuário", example = "João")
    private String nome;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    private String email;

    @Schema(description = "Descrição do usuário", example = "Sou uma pessoa legal")
    private String descricao;
    @Schema(description = "Data de nascimento do usuário", example = "2000-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Local de treino do usuário")
    private LocalTreinoUsuario localTreino;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalTreinoUsuario getLocalTreino() {
        return localTreino;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setLocalTreino(LocalTreinoUsuario localTreino) {
        this.localTreino = localTreino;
    }
}
