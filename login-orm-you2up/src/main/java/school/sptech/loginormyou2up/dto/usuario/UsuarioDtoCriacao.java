package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.domain.Treino;

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


    private String academia;

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
