package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioDtoJson { // essa classe mostra o usuario sem mostrar o seu treino
    private Integer id;
    @Schema(description = "Nome do usuário", example = "Natalia")
    private String nome;
    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private String email;

    public UsuarioDtoJson(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuarioDtoJson() {
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
}
