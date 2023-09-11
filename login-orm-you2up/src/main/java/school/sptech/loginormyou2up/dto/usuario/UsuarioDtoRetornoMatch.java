package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;

import java.time.LocalDate;

public class UsuarioDtoRetornoMatch {

    //Essa DTO retorna as informações do usuário nos endpoints de match

    private int id;

    @Schema(description = "Nome do usuário", example = "João")
    private String nome;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    private String email;

    @Schema(description = "Descrição do usuário", example = "Sou uma pessoa legal")
    private String descricao;

    @Schema(description = "Link da foto de perfil do usuário", example = "https://www.imagensempng.com.br/wp-content/uploads/2021/09/Icone-usuario-Png-1024x1024.png")
    private String fotoPerfil;

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

    public String getFotoPerfil() {
        return fotoPerfil;
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

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setLocalTreino(LocalTreinoUsuario localTreino) {
        this.localTreino = localTreino;
    }
}
