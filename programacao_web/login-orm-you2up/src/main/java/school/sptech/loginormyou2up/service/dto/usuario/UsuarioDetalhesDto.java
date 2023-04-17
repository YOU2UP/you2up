package school.sptech.loginormyou2up.service.dto.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.loginormyou2up.domain.Usuario;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    private final String nome;
    private final String email;
    private final String senha;

    public UsuarioDetalhesDto(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        senha = usuario.getSenha();
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