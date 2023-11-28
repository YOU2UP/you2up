package school.sptech.loginormyou2up.service.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.match.TbMatch;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.mapper.MatchMapper;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.repository.MatchRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    

    public List<MatchDtoResposta> getById(Integer id) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        List<MatchDtoResposta> listaRetorno = new ArrayList<>();

        listaRetorno.add(MatchMapper.convertToMatchDtoResposta(matchRepository.findById(id).get()));
        listaRetorno.add(inverteUsuarios(MatchMapper.convertToMatchDtoResposta(matchRepository.findById(id).get())));

        return listaRetorno;
    }

    public List<MatchDtoResposta> getAll() {
        if (matchRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum match encontrado");
        }

        List<TbMatch> listaRetorno = matchRepository.findAll();


        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDtoResposta(match),
                        inverteUsuarios(MatchMapper.convertToMatchDtoResposta(match))
                ))
                .collect(Collectors.toList());

        //return listaRetorno.stream().map(MatchMapper::convertToMatchDto).toList();
    }

    public List<MatchDtoResposta> getByIdUsuario(Integer id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<TbMatch> listaRetorno = matchRepository.getAllMatchsByUsuarioId(id);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum match encontrado");
        }

        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDtoResposta(match),
                        inverteUsuarios(MatchMapper.convertToMatchDtoResposta(match))
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


    public List<MatchDtoResposta> getMatchEntreUsuarios(int id1, int id2) {
        if (id1 == id2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não há matches entre o mesmo usuário");
        }

        List<TbMatch> listaRetorno = matchRepository.getMatchEntreUsuarios(id1, id2);

        if (listaRetorno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match não encontrado");
        }

        return listaRetorno.stream()
                .flatMap(match -> Stream.of(
                        MatchMapper.convertToMatchDtoResposta(match),
                        inverteUsuarios(MatchMapper.convertToMatchDtoResposta(match))
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

        return newDto;
    }

    public List<MatchDtoResposta> montaMatches(int idUsuario){
        if (!usuarioRepository.existsById(idUsuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        //apagando todos os matches daquele usuario do banco pois sempre que a aplicação é aberta o match é montado
        List<TbMatch> tbMatches = matchRepository.getAllMatchsByUsuarioId(idUsuario);
        tbMatches.forEach(match -> matchRepository.deleteById(match.getIdMatch()));


        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List<Usuario> usuariosCompativeis = new ArrayList<>();

        ///sequência de prioridade para matches
        //1 - mesmo estágio e mesma cidade
        //2 - mesma cidade
        //3 - mesmo estado e mesmo estágio
        //4 - mesmo estado

        usuariosCompativeis.addAll(usuarioRepository.findByEstagioAndCidade(usuario.getEstagio(), usuario.getLocalTreino().getCidade()));
        usuariosCompativeis.addAll(usuarioRepository.findByLocalTreinoCidadeIgnoreCase(usuario.getLocalTreino().getCidade()));
        usuariosCompativeis.addAll(usuarioRepository.findByLocalTreinoUfAndEstagio(usuario.getLocalTreino().getUf(), usuario.getEstagio()));
        usuariosCompativeis.addAll(usuarioRepository.findByLocalTreinoUfIgnoreCase(usuario.getLocalTreino().getUf()));

        if (usuariosCompativeis.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum match encontrado");
        }

        Set<Integer> usuariosCombinados = new HashSet<>(); // Conjunto para armazenar IDs de usuários combinados
        List<MatchDtoResposta> matchesRetorno = new ArrayList<>();

        usuariosCompativeis.forEach(usuario1 -> {
            // Verifica se a combinação já existe antes de criar uma nova
            if (!usuariosCombinados.contains(usuario1.getIdUsuario()) && !Objects.equals(usuario1.getIdUsuario(), usuario.getIdUsuario())) {
                usuariosCombinados.add(usuario1.getIdUsuario()); // Adiciona o ID do usuário combinado ao conjunto

                // Cria e salva o match no banco de dados
                TbMatch tbMatch = new TbMatch();
                tbMatch.setUsuario1(usuario);
                tbMatch.setUsuario2(usuario1);
                tbMatch.setDataMatch(LocalDate.now());
                matchRepository.save(tbMatch);

                matchesRetorno.add(MatchMapper.convertToMatchDtoResposta(tbMatch));
            }
        });

        return matchesRetorno;

    }

}
