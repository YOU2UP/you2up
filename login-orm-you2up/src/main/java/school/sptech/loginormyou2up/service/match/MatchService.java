package school.sptech.loginormyou2up.service.match;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public MatchDtoResposta criarMatch(MatchDtoCriacao dto) {
        if (dto.getUsuario1().getId().equals(dto.getUsuario2().getId())) {
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

    public List<MatchDtoResposta> getById(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        List<MatchDtoResposta> listaRetorno = new ArrayList<>();

        listaRetorno.add(MatchMapper.convertToMatchDto(matchRepository.findById(id).get()));
        listaRetorno.add(inverteUsuarios(MatchMapper.convertToMatchDto(matchRepository.findById(id).get())));

        return listaRetorno;
    }

    public List<MatchDtoResposta> getAll() {
        if (matchRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum match encontrado");
        }

        List<Match> listaRetorno = matchRepository.findAll();


        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDto(match),
                        inverteUsuarios(MatchMapper.convertToMatchDto(match))
                ))
                .collect(Collectors.toList());

        //return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public List<MatchDtoResposta> getByIdUsuario(Integer id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<Match> listaRetorno = matchRepository.getAllMatchsByUsuarioId(id);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum match encontrado");
        }

        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDto(match),
                        inverteUsuarios(MatchMapper.convertToMatchDto(match))
                ))
                .collect(Collectors.toList());

        //return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
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

        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDto(match),
                        inverteUsuarios(MatchMapper.convertToMatchDto(match))
                ))
                .collect(Collectors.toList());

        //return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public boolean matchExiste(int id1, int id2) {
        return matchRepository.countMatches(id1, id2) > 0;
    }


    private MatchDtoResposta inverteUsuarios(MatchDtoResposta dto) {
        MatchDtoResposta newDto = new MatchDtoResposta();

        newDto.setId(dto.getId());
        newDto.setUsuario1(dto.getUsuario2());
        newDto.setUsuario2(dto.getUsuario1());
        newDto.setDataMatch(dto.getDataMatch());
        newDto.setAtivo(dto.isAtivo());

        return newDto;
    }

}
