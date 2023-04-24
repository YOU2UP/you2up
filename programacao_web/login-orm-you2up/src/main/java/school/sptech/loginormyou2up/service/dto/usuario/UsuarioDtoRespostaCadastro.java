package school.sptech.loginormyou2up.service.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class UsuarioDtoRespostaCadastro {
    private Integer id;
    @Schema(description = "Nome do usuário", example = "Natalia")
    private String nome;


    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private String email;


    @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Sexo do usuário", example = "F")
    private String sexo;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
