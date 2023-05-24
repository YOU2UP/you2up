package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.dto.usuario.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioDtoJson convertToUsuarioDtoJson(Usuario usuario){
        UsuarioDtoJson dto = new UsuarioDtoJson();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public static UsuarioDtoResposta convertToDtoResposta(Usuario usuario) {
        UsuarioDtoResposta dto = new UsuarioDtoResposta();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setNotificacoes(NotificacaoMapper.convertToNotificacaoDtoResposta(usuario.getNotificacoes()));
        dto.setMetaTreinos(usuario.getMetaTreinos());
        dto.setEstagio(usuario.getEstagio());
        dto.setLocalTreino(usuario.getLocalTreino());


        List<TreinoDtoJsonUsuario> listaTreino = new ArrayList<>();

        for (TreinoHasUsuario tu : usuario.getTreinos()){
            listaTreino.add(TreinoMapper.convertToTreinoDtoJsonUsuario(tu.getTreino()));
        }

        dto.setTreinos(listaTreino);

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
            dto.setNotificacoes(NotificacaoMapper.convertToNotificacaoDtoResposta(u.getNotificacoes()));
            dto.setMetaTreinos(u.getMetaTreinos());
            dto.setEstagio(u.getEstagio());
            dto.setLocalTreino(u.getLocalTreino());

            List<TreinoDtoJsonUsuario> listaTreino = new ArrayList<>();

            for (TreinoHasUsuario tu : u.getTreinos()){
                listaTreino.add(TreinoMapper.convertToTreinoDtoJsonUsuario(tu.getTreino()));
            }

            dto.setTreinos(listaTreino);

            listaRetorno.add(dto);
        }

        return listaRetorno;
    }

    public static UsuarioDtoRespostaCadastro convertToUsuarioDtoRespostaCadastro(Usuario usuario){
        UsuarioDtoRespostaCadastro dto = new UsuarioDtoRespostaCadastro();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public static Usuario convertToUsuario(UsuarioDtoCriacao usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setDescricao(usuarioDto.getDescricao());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setEstagio(usuarioDto.getEstagio());
        usuario.setMetaTreinos(0);
        usuario.setNotificacoes(new ArrayList<>());
        usuario.setLocalTreino(TreinoMapper.convertToLocalTreinoUsuario(usuarioDto.getLocalTreino()));

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


    public static UsuarioResumoDto convertToUsuarioResumoDto(Usuario usuario) {
        UsuarioResumoDto dto = new UsuarioResumoDto();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        return dto;
    }
}
