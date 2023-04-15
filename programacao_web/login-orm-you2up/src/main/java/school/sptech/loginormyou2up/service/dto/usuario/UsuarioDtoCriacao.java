package school.sptech.loginormyou2up.service.dto.usuario;

import school.sptech.loginormyou2up.domain.Treino;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioDtoCriacao {
    private Integer id;

    @Size(min = 3)
    private String nome;

    @Email
    private String email;
    private String senha;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double notaMedia;

    @Past
    private LocalDate dataNascimento;

    private String descricao;

    @NotBlank
    private String estagio; // iniciante, intermediário, avançado

    private Treino treino;


    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
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

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
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
