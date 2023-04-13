package school.sptech.loginormyou2up.models.mapper;

import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.models.dto.UsuarioDto;

public class UsuarioMapper {
    UsuarioDto usuarioDto = new UsuarioDto();

    public UsuarioDto adicionaMapper(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();

        dto.setNome(usuario.getNome());
        dto.setUsuario(usuario.getEmail());

        return dto;
    }
}
