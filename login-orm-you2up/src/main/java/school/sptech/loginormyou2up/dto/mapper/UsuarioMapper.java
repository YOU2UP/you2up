package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoJson;
import school.sptech.loginormyou2up.dto.usuario.UsuarioTokenDto;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoCriacao;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRespostaCadastro;

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
        dto.setNotaMedia(usuario.getNotaMedia());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setNotificacoes(NotificacaoMapper.convertToNotificacaoDtoResposta(usuario.getNotificacoes()));
        dto.setSexo(usuario.getSexo());
        dto.setMetaTreinos(usuario.getMetaTreinos());
        dto.setEstagio(usuario.getEstagio());

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
            dto.setNotaMedia(u.getNotaMedia());
            dto.setDataNascimento(u.getDataNascimento());
            dto.setNotificacoes(NotificacaoMapper.convertToNotificacaoDtoResposta(u.getNotificacoes()));
            dto.setSexo(u.getSexo());
            dto.setMetaTreinos(u.getMetaTreinos());
            dto.setEstagio(u.getEstagio());

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
        dto.setSexo(usuario.getSexo());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public static Usuario convertToUsuario(UsuarioDtoCriacao usuarioDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setNotaMedia(0.0);
        usuario.setDescricao(usuarioDto.getDescricao());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setEstagio(usuarioDto.getEstagio());
        usuario.setMetaTreinos(0);
        usuario.setNotificacoes(new ArrayList<>());

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
