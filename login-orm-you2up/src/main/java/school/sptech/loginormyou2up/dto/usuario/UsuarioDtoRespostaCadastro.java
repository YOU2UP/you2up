package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class
UsuarioDtoRespostaCadastro {

    @Schema(description = "Id do usuário", example = "1")
    private Integer id;
    @Schema(description = "Nome do usuário", example = "Natalia")
    private String nome;


    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private String email;


    @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
    private LocalDate dataNascimento;

    @Schema(description = "Sexo do usuário", example = "F")
    private String sexo;

    @Schema(description = "Data de entrada do usuário", example = "2021-01-01")
    private LocalDateTime dataCriacaoConta;

    public UsuarioDtoRespostaCadastro(Integer id, String nome, String email, LocalDate dataNascimento, String sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public UsuarioDtoRespostaCadastro() {
    }

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

    public LocalDateTime getDataCriacaoConta() {
        return dataCriacaoConta;
    }

    public void setDataCriacaoConta(LocalDateTime dataCriacaoConta) {
        this.dataCriacaoConta = dataCriacaoConta;
    }
}

