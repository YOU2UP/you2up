package school.sptech.loginormyou2up.service.avaliacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.Avaliacao;
import school.sptech.loginormyou2up.dto.avaliacao.AvaliacaoRespostaDto;
import school.sptech.loginormyou2up.dto.mapper.AvaliacaoMapper;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.repository.AvaliacaoRepository;

import java.util.List;
import java.util.Objects;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;


    public AvaliacaoRespostaDto save(Avaliacao avaliacao) {
        if(Objects.isNull(avaliacao)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Avaliação não pode ser nula");
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
}
