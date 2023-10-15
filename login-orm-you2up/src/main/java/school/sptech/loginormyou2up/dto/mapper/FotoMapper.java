package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.foto.Foto;
import school.sptech.loginormyou2up.dto.FotoRespostaDto;

import java.util.List;

public class FotoMapper {

    public static FotoRespostaDto converterFotoParaFotoRespostaDto(Foto foto){
        FotoRespostaDto fotoRespostaDto = new FotoRespostaDto();
        fotoRespostaDto.setIdFoto(foto.getIdFoto());
        fotoRespostaDto.setUrl(foto.getUrl());
        fotoRespostaDto.setUsuario(UsuarioMapper.convertToUsuarioResumoDto(foto.getUsuario()));
        return fotoRespostaDto;
    }

    public static List<FotoRespostaDto> converterFotoParaFotoRespostaDto(List<Foto> fotos){
        return fotos.stream().map(FotoMapper::converterFotoParaFotoRespostaDto).toList();
    }

}
