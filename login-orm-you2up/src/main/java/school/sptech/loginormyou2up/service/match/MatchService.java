package school.sptech.loginormyou2up.service.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.mapper.MatchMapper;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
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


    public Match criarMatch(MatchDtoCriacao dto) {
        if (dto.getUsuario1().getId().equals(dto.getUsuario2().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível criar um match com o mesmo usuário");
        }

        if (usuarioRepository.findById(dto.getUsuario1().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 1 não encontrado");
        }

        if (usuarioRepository.findById(dto.getUsuario2().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário 2 não encontrado");
        }

        return matchRepository.save(MatchMapper.convertToMatch(dto));
    }

    public Match getById(Integer id) {
        return matchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado"));
    }

    public List<Match> getAll() {
        if (matchRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum match encontrado");
        }

        return matchRepository.findAll();
    }

    public List<Match> getByIdUsuario(Integer id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<Match> listaRetorno = matchRepository.getAllMatchsByUsuarioId(id);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum match encontrado");
        }

        return listaRetorno;
    }

    public void deleteById(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        matchRepository.deleteById(id);
    }

    public Match putById(Integer id, MatchDtoCriacao dto) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        if (dto.getUsuario1().getId().equals(dto.getUsuario2().getId())) {
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

        return matchRepository.save(match);
    }

    public Match getMatchEntreUsuarios(int id1, int id2) {
        Optional<Match> matchOpt = matchRepository.getMatchEntreUsuarios(id1, id2);

        if (matchOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        return matchOpt.get();
    }
}
