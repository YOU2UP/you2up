package school.sptech.loginormyou2up.service.dto.mapper;

import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoCriacao;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoJsonTreino;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;
import school.sptech.loginormyou2up.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioDtoJsonTreino convertToUsuarioDtoJsonTreino(Usuario usuario){
        UsuarioDtoJsonTreino dto = new UsuarioDtoJsonTreino();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        return dto;
    }


    public static UsuarioDtoResposta convertToDtoResposta(UsuarioDtoCriacao usuario) {
        UsuarioDtoResposta dto = new UsuarioDtoResposta();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setTreino(usuario.getTreino());

        return dto;
    }

    public static UsuarioDtoResposta convertToDtoResposta(Usuario usuario) {
        UsuarioDtoResposta dto = new UsuarioDtoResposta();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setTreino(usuario.getTreino());

        return dto;
    }

    public static List<UsuarioDtoResposta> convertToDtoResposta(List<Usuario> usuarios){
        List<UsuarioDtoResposta> listaRetorno =  new ArrayList<>();
        for (Usuario u: usuarios) {
            UsuarioDtoResposta dto = new UsuarioDtoResposta();
            dto.setId(u.getId());
            dto.setNome(u.getNome());
            dto.setEmail(u.getEmail());
            dto.setDataNascimento(u.getDataNascimento());
            dto.setTreino(u.getTreino());

            listaRetorno.add(dto);
        }

        return listaRetorno;
    }

    public static Usuario convertToUsuario(UsuarioDtoCriacao usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setTreino(usuarioDto.getTreino());
        usuario.setNotaMedia(usuarioDto.getNotaMedia());
        usuario.setDescricao(usuarioDto.getDescricao());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setEstagio(usuarioDto.getEstagio());


        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static UsuarioTokenDto of(UsuarioDtoCriacao usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }


}
