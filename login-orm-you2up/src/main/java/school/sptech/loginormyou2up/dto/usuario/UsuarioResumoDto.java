package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResumoDto {
    private int id;

    @Schema(description = "Nome do usuário", example = "João")
    private String nome;

    @Schema(description = "Email do usuário", example = "joao@email.com")
    private String email;

    @Schema(description = "Quantidade de treinos realizados pelo usuário", example = "10")
    private int metaTreinos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMetaTreinos() {
        return metaTreinos;
    }

    public void setMetaTreinos(int metaTreinos) {
        this.metaTreinos = metaTreinos;
    }
}
