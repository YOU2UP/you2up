package school.sptech.loginormyou2up.service.foto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.foto.Foto;
import school.sptech.loginormyou2up.dto.FotoRespostaDto;
import school.sptech.loginormyou2up.dto.mapper.FotoMapper;
import school.sptech.loginormyou2up.repository.FotoRepository;

import java.util.List;
import java.util.Objects;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;


    public List<FotoRespostaDto> findAll(){
        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findAll());
    }

    public FotoRespostaDto save(Foto foto){
        if (Objects.isNull(foto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto não pode ser nula");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.save(foto));
    }

    public FotoRespostaDto findById(int id){
        if (fotoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findById(id).get());
    }

    public List<FotoRespostaDto> getFeedFotos(int idUsuario){
        if (fotoRepository.findByIdUsuario(idUsuario).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista vazia");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findByIdUsuario(idUsuario));
    }

    public void deleteById(Integer id){
        if (fotoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        fotoRepository.deleteById(id);
    }



}
