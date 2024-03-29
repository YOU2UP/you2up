package school.sptech.loginormyou2up.api.configuration.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.loginormyou2up.service.usuario.autenticacao.AutenticacaoService;

import java.util.stream.Stream;

public class AutenticacaoProvider implements AuthenticationProvider {

    private final AutenticacaoService usuarioAutenticacaoService;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoProvider(AutenticacaoService usuarioAutenticacaoService, PasswordEncoder passwordEncoder) {
        this.usuarioAutenticacaoService = usuarioAutenticacaoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userDetails = usuarioAutenticacaoService.loadUserByUsername(username);

        if(passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("Usuário ou Senha inválidos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
