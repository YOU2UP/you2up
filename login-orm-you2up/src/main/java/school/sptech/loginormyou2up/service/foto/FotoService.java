package school.sptech.loginormyou2up.service.foto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.foto.Foto;
import school.sptech.loginormyou2up.domain.foto.FotoPerfil;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.FotoRespostaDto;
import school.sptech.loginormyou2up.dto.mapper.FotoMapper;
import school.sptech.loginormyou2up.repository.FotoPerfilRepository;
import school.sptech.loginormyou2up.repository.FotoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import java.util.List;
import java.util.Objects;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private FotoPerfilRepository fotoPerfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<FotoRespostaDto> findAllFotos(){
        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findAll());
    }

    public List<FotoRespostaDto> findAllFotoPerfil(){
        return FotoMapper.converterFotoPerfilParaFotoRespostaDto(fotoPerfilRepository.findAll());
    }


    public FotoRespostaDto save(Foto foto){
        if (Objects.isNull(foto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto não pode ser nula");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.save(foto));
    }

    public FotoRespostaDto save(FotoPerfil foto){
        if (Objects.isNull(foto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto não pode ser nula");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoPerfilRepository.save(foto));
    }

    public FotoRespostaDto findById(int id){
        if (fotoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findById(id).get());
    }

    public FotoRespostaDto findFotoPerfilById(int id){
        if (fotoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoPerfilRepository.findById(id).get());
    }

    public List<FotoRespostaDto> getFeedFotos(int idUsuario){
        if (fotoRepository.findByIdUsuario(idUsuario).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista vazia");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoRepository.findByIdUsuario(idUsuario));
    }


    public FotoRespostaDto getFotoPerfilUsuario(int idUsuario){
        if (fotoRepository.findByIdUsuario(idUsuario).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Lista vazia");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoPerfilRepository.findByUsuarioIdUsuario(idUsuario).get());
    }


    public void deleteById(Integer id){
        if (fotoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        fotoRepository.deleteById(id);
    }

    public void deleteFotoPerfilById(Integer id){
        if (fotoPerfilRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        fotoPerfilRepository.deleteById(id);
    }

    public void deleteFotoPerfilByIdUsuario(Integer id){
        if (fotoPerfilRepository.findByUsuarioIdUsuario(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foto não encontrada");
        }

        fotoPerfilRepository.deleteById(id);
    }

    public FotoRespostaDto saveFotoPerfil(FotoPerfil fotoPerfil){
        if (Objects.isNull(fotoPerfil)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto não pode ser nula");
        }

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoPerfilRepository.save(fotoPerfil));
    }

    public FotoRespostaDto updateFotoPerfil(int idUsuario, FotoPerfil fotoPerfil){
        if (Objects.isNull(fotoPerfil)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Foto não pode ser nula");
        } else if (!usuarioRepository.existsById(idUsuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não existe");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        System.out.println("oi");
        int idFotoPerfil = usuario.getFotoPerfil().getIdFotoPerfil();
        fotoPerfil.setIdFotoPerfil(idFotoPerfil);
        fotoPerfil.setUsuario(usuario);
        fotoPerfil.setUrl(fotoPerfil.getUrl());

        return FotoMapper.converterFotoParaFotoRespostaDto(fotoPerfilRepository.save(fotoPerfil));
    }

    public FotoPerfil criaFotoPadrao(){
        FotoPerfil fotoPerfil = new FotoPerfil();
        fotoPerfil.setUrl("https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png");
        return fotoPerfilRepository.save(fotoPerfil);
    }

}
