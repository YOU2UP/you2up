package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;

import java.time.LocalDateTime;

public class MatchMapper {

    public static Match convertToMatch(MatchDtoCriacao dto){
        Match match = new Match();
        match.setUsuario1(dto.getUsuario1());
        match.setUsuario2(dto.getUsuario2());
        match.setDataMatch(LocalDateTime.now());
        match.setAtivo(true);

        return match;
    }

    public static MatchDtoResposta convertToMatchDto(Match match){
        MatchDtoResposta dto = new MatchDtoResposta();

        dto.setId(match.getId());
        dto.setAtivo(match.isAtivo());
        dto.setDataMatch(match.getDataMatch());
        dto.setUsuario1(UsuarioMapper.convertToUsuarioResumoDto(match.getUsuario1()));
        dto.setUsuario2(UsuarioMapper.convertToUsuarioResumoDto(match.getUsuario2()));

        return dto;
    }
}
