package school.sptech.loginormyou2up.service.avaliacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.avaliacao.Avaliacao;
import school.sptech.loginormyou2up.dto.avaliacao.AvaliacaoRespostaDto;
import school.sptech.loginormyou2up.dto.mapper.AvaliacaoMapper;
import school.sptech.loginormyou2up.repository.AvaliacaoRepository;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import java.util.List;
import java.util.Objects;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    public AvaliacaoRespostaDto save(Avaliacao avaliacao) {
        if(Objects.isNull(avaliacao)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avaliação não pode ser nula");
        } else if (treinoRepository.findById(avaliacao.getTreino().getIdTreino()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Treino não existe");
        } else if (!treinoRepository.findById(avaliacao.getTreino().getIdTreino()).get().isRealizado()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível avaliar um treino que ainda não foi realizado");
        }

        return AvaliacaoMapper.convertToAvaliacaoRespostaDto(avaliacaoRepository.save(avaliacao));
    }

    public List<AvaliacaoRespostaDto> findAll() {
        if (avaliacaoRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "lista vazia");
        }

        return AvaliacaoMapper.convertToAvaliacaoRespostaDto(avaliacaoRepository.findAll());
    }

    public AvaliacaoRespostaDto findById(int id) {
        if (avaliacaoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
        }

        return AvaliacaoMapper.convertToAvaliacaoRespostaDto(avaliacaoRepository.findById(id).get());
    }

    public void deleteById(Integer id) {
        if (avaliacaoRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
        }

        avaliacaoRepository.deleteById(id);
    }

    public List<AvaliacaoRespostaDto> findByIdAvaliador(int id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return AvaliacaoMapper.convertToAvaliacaoRespostaDto(avaliacaoRepository.findByIdAvaliador(id));

    }

    public List<AvaliacaoRespostaDto> findByIdAvaliado(int id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return AvaliacaoMapper.convertToAvaliacaoRespostaDto(avaliacaoRepository.findByIdAvaliado(id));

    }

    public double getMediaAvaliacaoUsuario(int id){
        if (usuarioRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Double media = avaliacaoRepository.getMediaAvaliacaoUsuarioById(id);

        if (Objects.isNull(media)){
           return 0;
        }

        return media;
    }
}
