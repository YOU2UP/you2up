package school.sptech.loginormyou2up.dto.mapper;

import org.apache.catalina.mapper.Mapper;
import school.sptech.loginormyou2up.domain.Avaliacao;
import school.sptech.loginormyou2up.domain.Usuario;
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
        AvaliacaoRespostaDto avaliacaoRespostaDto = new AvaliacaoRespostaDto();
        List<AvaliacaoRespostaDto> listaRetorno = new ArrayList<>();

        for (Avaliacao avaliacao: avaliacoes) {
            avaliacaoRespostaDto.setId(avaliacao.getId());
            avaliacaoRespostaDto.setAvaliador(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliador()));
            avaliacaoRespostaDto.setAvaliado(UsuarioMapper.convertToUsuarioResumoDto(avaliacao.getAvaliado()));
            avaliacaoRespostaDto.setNota(avaliacao.getNota());

            listaRetorno.add(avaliacaoRespostaDto);
        }

        return listaRetorno;
    }

}
