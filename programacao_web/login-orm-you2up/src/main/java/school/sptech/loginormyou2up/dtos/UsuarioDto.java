package school.sptech.loginormyou2up.dtos;

import school.sptech.loginormyou2up.classes.Usuario;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioDto {

    public UsuarioDto(Usuario usuario){
        setId(usuario.getId());
        setEmail(usuario.getEmail());
        setNome(usuario.getNome());
        setDataNascimento(usuario.getDataNascimento());
        setNotaMedia(usuario.getNotaMedia());
    }


    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Double notaMedia;
    private String descricao;


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

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
