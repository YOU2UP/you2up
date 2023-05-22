package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.dto.treino.LocalTreinoCriacaoDto;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioDtoCriacao {

    private Integer id;

    @Schema(description = "Nome do usuário", example = "Natalia")
    private String nome;

    @Email
    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "mypassword")
    private String senha;

    @Past
    @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Descrição do usuário", example = "Usuário ativo")
    private String descricao;

    @NotBlank
    @Schema(description = "Estágio do usuário (iniciante, intermediário, avançado)", example = "iniciante")
    private String estagio;

    @Size(max = 1)
    @NotBlank
    @Schema(description = "Sexo do usuário", example = "F")
    private String sexo;

    private LocalTreinoCriacaoDto localTreino;


    public LocalTreinoCriacaoDto getLocalTreino() {
        return localTreino;
    }

    public void setLocalTreino(LocalTreinoCriacaoDto localTreino) {
        this.localTreino = localTreino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
