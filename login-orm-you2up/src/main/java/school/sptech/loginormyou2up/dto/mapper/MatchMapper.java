package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;

public class MatchMapper {

    public static MatchDtoResposta convertToMatchDtoResposta(Match match){
        MatchDtoResposta dto = new MatchDtoResposta();

        dto.setId(match.getIdMatch());
        dto.setUsuario1(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(match.getUsuario1()));
        dto.setUsuario2(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(match.getUsuario2()));

        return dto;
    }
}
