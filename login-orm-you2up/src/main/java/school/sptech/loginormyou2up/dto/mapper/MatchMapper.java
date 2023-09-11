package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;

import java.time.LocalDateTime;

public class MatchMapper {

    public static Match convertToMatch(MatchDtoCriacao dto){
        Match match = new Match();
        match.setDataMatch(LocalDateTime.now());
        match.setUsuario1(dto.getUsuario1());
        match.setUsuario2(dto.getUsuario2());
        match.setAtivo(true);

        return match;
    }

    public static MatchDtoResposta convertToMatchDto(Match match){
        MatchDtoResposta dto = new MatchDtoResposta();

        dto.setId(match.getId());
        dto.setAtivo(match.isAtivo());
        dto.setDataMatch(match.getDataMatch());
        dto.setUsuario1(UsuarioMapper.convertToUsuarioDtoRetornoMatch(match.getUsuario1()));
        dto.setUsuario2(UsuarioMapper.convertToUsuarioDtoRetornoMatch(match.getUsuario2()));

        return dto;
    }
}
