package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    @Schema(description = "Nome do usuário", example = "Natalia")
    private final String nome;
    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private final String email;
    @Schema(description = "Senha do usuário", example = "mypassword")
    private final String senha;


    public UsuarioDetalhesDto(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
    }

    public UsuarioDetalhesDto(String nome, String email, String senha, String fotoPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
