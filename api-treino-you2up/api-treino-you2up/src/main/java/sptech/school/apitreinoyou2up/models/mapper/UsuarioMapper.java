package sptech.school.apitreinoyou2up.models.mapper;

import sptech.school.apitreinoyou2up.domain.Usuario;
import sptech.school.apitreinoyou2up.models.dto.UsuarioDto;

public class UsuarioMapper {

    UsuarioDto usuarioDto = new UsuarioDto();

    public UsuarioDto adicionaMapper(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();

        dto.setNome(usuario.getNome());
        dto.setUsuario(usuario.getUsuario());

        return dto;
    }

}
