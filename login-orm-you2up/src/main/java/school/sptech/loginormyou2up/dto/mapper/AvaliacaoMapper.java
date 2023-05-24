package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.avaliacao.Avaliacao;
import school.sptech.loginormyou2up.dto.avaliacao.AvaliacaoRespostaDto;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoMapper {

    public static AvaliacaoRespostaDto convertToAvaliacaoRespostaDto(Avaliacao avaliacao) {
        AvaliacaoRespostaDto avaliacaoRespostaDto = new AvaliacaoRespostaDto();

        avaliacaoRespostaDto.setId(avaliacao.getId());
        avaliacaoRespostaDto.setAvaliador(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliador()));
        avaliacaoRespostaDto.setAvaliado(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliado()));
        avaliacaoRespostaDto.setNota(avaliacao.getNota());

        return avaliacaoRespostaDto;
    }

    public static List<AvaliacaoRespostaDto> convertToAvaliacaoRespostaDto(List<Avaliacao> avaliacoes) {
        List<AvaliacaoRespostaDto> listaRetorno = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            AvaliacaoRespostaDto avaliacaoRespostaDto = new AvaliacaoRespostaDto();
            avaliacaoRespostaDto.setId(avaliacao.getId());
            avaliacaoRespostaDto.setAvaliador(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliador()));
            avaliacaoRespostaDto.setAvaliado(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliado()));
            avaliacaoRespostaDto.setNota(avaliacao.getNota());

            listaRetorno.add(avaliacaoRespostaDto);
        }

        return listaRetorno;
    }

}
