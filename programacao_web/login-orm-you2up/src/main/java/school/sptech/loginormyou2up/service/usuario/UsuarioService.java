package school.sptech.loginormyou2up.service.usuario;

import io.micrometer.core.instrument.step.StepRegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoCriacao;
import school.sptech.loginormyou2up.service.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.loginormyou2up.service.usuario.autenticacao.dto.UsuarioTokenDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UsuarioDtoResposta criar(UsuarioDtoCriacao usuarioDtoCriacao){
        Usuario novoUsuario = UsuarioMapper.convertToUsuario(usuarioDtoCriacao);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCriptografada);

        return UsuarioMapper.convertToDtoResposta(usuarioRepository.save(novoUsuario));
    }

    public UsuarioTokenDto  autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}
