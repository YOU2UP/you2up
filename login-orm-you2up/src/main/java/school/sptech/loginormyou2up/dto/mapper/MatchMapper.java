package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.match.TbMatch;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;

public class MatchMapper {

    public static MatchDtoResposta convertToMatchDtoResposta(TbMatch tbMatch){
        MatchDtoResposta dto = new MatchDtoResposta();

        dto.setId(tbMatch.getIdMatch());
        dto.setUsuario1(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(tbMatch.getUsuario1()));
        dto.setUsuario2(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(tbMatch.getUsuario2()));
        dto.setDataMatch(tbMatch.getDataMatch());

        return dto;
    }
}
