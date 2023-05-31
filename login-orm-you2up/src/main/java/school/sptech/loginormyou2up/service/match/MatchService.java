package school.sptech.loginormyou2up.service.match;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.mapper.MatchMapper;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.repository.MatchRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public MatchDtoResposta criarMatch(MatchDtoCriacao dto) {
        if (dto.getUsuario1().getId() == dto.getUsuario2().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível criar um match com o mesmo usuário");
        }

        if (matchExiste(dto.getUsuario1().getId(), dto.getUsuario2().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match já existe");
        }

        if (usuarioRepository.findById(dto.getUsuario1().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 1 não encontrado");
        }

        if (usuarioRepository.findById(dto.getUsuario2().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 2 não encontrado");
        }

        Match match = MatchMapper.convertToMatch(dto);
        return MatchMapper.convertToMatchDto(matchRepository.save(match));
    }

    public MatchDtoResposta getById(Integer id) {
        if (matchRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        return MatchMapper.convertToMatchDto(matchRepository.findById(id).get());
    }

    public List<MatchDtoResposta> getAll() {
        if (matchRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum match encontrado");
        }

        List<Match> listaRetorno = matchRepository.findAll();

        return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public List<MatchDtoResposta> getByIdUsuario(Integer id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<Match> listaRetorno = matchRepository.getAllMatchsByUsuarioId(id);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum match encontrado");
        }

        return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public void deleteById(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        matchRepository.deleteById(id);
    }

    public MatchDtoResposta putById(Integer id, MatchDtoCriacao dto) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        if (dto.getUsuario1().getId() == dto.getUsuario2().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível criar um match com o mesmo usuário");
        }

        if (usuarioRepository.findById(dto.getUsuario1().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 1 não encontrado");
        }

        if (usuarioRepository.findById(dto.getUsuario2().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 2 não encontrado");
        }

        Match match = MatchMapper.convertToMatch(dto);
        match.setId(id);

        return MatchMapper.convertToMatchDto(matchRepository.save(match));
    }

    public List<MatchDtoResposta> getMatchEntreUsuarios(int id1, int id2) {
        if (id1 == id2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não há matches entre o mesmo usuário");
        }

        List<Match> listaRetorno = matchRepository.getMatchEntreUsuarios(id1, id2);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public boolean matchExiste(int id1, int id2){
        return matchRepository.countMatches(id1, id2) > 0;
    }

}
