package school.sptech.loginormyou2up.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.loginormyou2up.domain.usuario.Usuario;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    @Schema(description = "Nome do usuário", example = "Natalia")
    private final String nome;
    @Schema(description = "E-mail do usuário", example = "natalia@example.com")
    private final String email;
    @Schema(description = "Senha do usuário", example = "mypassword")
    private final String senha;

    @Schema(description = "Link da foto de perfil do usuário", example = "https://www.imagensempng.com.br/wp-content/uploads/2021/09/Icone-usuario-Png-1024x1024.png")
    private final String fotoPerfil;

    public UsuarioDetalhesDto(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        fotoPerfil = usuario.getFotoPerfil();
    }

    public UsuarioDetalhesDto(String nome, String email, String senha, String fotoPerfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNome() {
        return nome;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
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
