package school.sptech.loginormyou2up.service.usuario;

import org.hibernate.hql.internal.ast.tree.TableReferenceNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.treino.QuantidadeTreinosPorDiaSemanaDto;
import school.sptech.loginormyou2up.dto.usuario.*;
import school.sptech.loginormyou2up.repository.LocalTreinoUsuarioRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.service.avaliacao.AvaliacaoService;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.match.MatchService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LocalTreinoUsuarioRepository localTreinoUsuarioRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private MatchService matchService;

    public UsuarioDtoRespostaCadastro criar(UsuarioDtoCriacao usuarioDtoCriacao) {
        if (usuarioRepository.findByEmail(usuarioDtoCriacao.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        Usuario novoUsuario = UsuarioMapper.convertToUsuario(usuarioDtoCriacao);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCriptografada);

        LocalTreinoUsuario localTreinoCadastrado = localTreinoUsuarioRepository.save(novoUsuario.getLocalTreino());
        novoUsuario.getLocalTreino().setIdLocalTreino(localTreinoCadastrado.getIdLocalTreino());

        return UsuarioMapper.convertToUsuarioDtoRespostaCadastro(usuarioRepository.save(novoUsuario));
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public List<UsuarioDtoResposta> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        //transformando a lista de usuários em uma lista de DTOs
        List<UsuarioDtoResposta> listaRetorno = usuarios.stream().map(UsuarioMapper::convertToDtoResposta).toList();


        //setando a média em todos os usuários
        listaRetorno.forEach(usuarioDtoResposta -> {
            usuarioDtoResposta.setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(usuarioDtoResposta.getId()));
        });

        //adicionando os matches em todos os usuários
        listaRetorno = listaRetorno.stream().map(this::adicionaMatches).toList();

        return listaRetorno;
    }

    public QuantidadeTreinosPorDiaSemanaDto getQuantidadeTreinosPorDiaSemana(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        List<TreinoHasUsuario> treinos = usuarioOpt.get().getTreinos();
        List<LocalDateTime> horarioTreinos = treinos.stream().map(TreinoHasUsuario::getInicioTreino).toList();
        List<DayOfWeek> diasDaSemana = horarioTreinos.stream().map(LocalDateTime::getDayOfWeek).toList();

        QuantidadeTreinosPorDiaSemanaDto dto = new QuantidadeTreinosPorDiaSemanaDto();

        for (DayOfWeek dia : diasDaSemana) {
            switch (dia) {
                case SUNDAY -> dto.incrementaDomingo();
                case MONDAY -> dto.incrementaSegunda();
                case TUESDAY -> dto.incrementaTerca();
                case WEDNESDAY -> dto.incrementaQuarta();
                case THURSDAY -> dto.incrementaQuinta();
                case FRIDAY -> dto.incrementaSexta();
                case SATURDAY -> dto.incrementaSabado();
            }
        }

        return dto;
    }

    public UsuarioDtoResposta getById(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        UsuarioDtoResposta dto = UsuarioMapper.convertToDtoResposta(usuarioOpt.get());
        dto.setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(id));

        dto = adicionaMatches(dto);

        return dto;
    }

    public void deleteById(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        usuarioRepository.deleteById(id);

    }


    public UsuarioDtoResposta putById(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            return UsuarioMapper.convertToDtoResposta(usuarioRepository.save(usuario));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ListaObj<UsuarioDtoResposta> menorParaMaior() {
        if (usuarioRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            List<Usuario> lista = usuarioRepository.findAll();
            ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());

            for (int i = 0; i < lista.size(); i++) {
                listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
            }

            listaUser = bubbleSortNota(listaUser);

            for (int i = 0; i < listaUser.getTamanho(); i++) {
                listaUser.getElemento(i).setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(listaUser.getElemento(i).getId()));
            }

            return listaUser;
        }
    }

    public ListaObj<UsuarioDtoResposta> buscarPorNota(Double nota) {
        if (usuarioRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Usuario> lista = usuarioRepository.findAll();
        ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
        }

        listaUser = bubbleSortNota(listaUser);

        ListaObj<UsuarioDtoResposta> encontrados = new ListaObj<>(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            UsuarioDtoResposta encontrado = pesquisaBinariaPorNota(listaUser, nota);
            if (encontrado != null) {
                encontrados.adicionaNoIndice(encontrado, encontrados.getTamanho());
            }

        }

        ListaObj<UsuarioDtoResposta> newEncontrados = new ListaObj<>(encontrados.getTamanho());
        // nova lista para não haver retorno com vários nulls no json

        for (int i = 0; i < encontrados.getTamanho(); i++) {
            newEncontrados.adicionaNoIndice(encontrados.getElemento(i), i);
        }


        if (newEncontrados.getTamanho() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return newEncontrados;
        }

    }

    private UsuarioDtoResposta adicionaMatches(UsuarioDtoResposta usuarioDtoResposta) {
        try {
            usuarioDtoResposta.setMatches(matchService.getByIdUsuario(usuarioDtoResposta.getId()));
        } catch (ResponseStatusException e) {
            usuarioDtoResposta.setMatches(new ArrayList<>());
        }

        return usuarioDtoResposta;
    }

    private ListaObj<UsuarioDtoResposta> adicionaMatches(ListaObj<UsuarioDtoResposta> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            try {
                lista.getElemento(i).setMatches(matchService.getByIdUsuario(lista.getElemento(i).getId()));
            } catch (ResponseStatusException e) {
                lista.getElemento(i).setMatches(new ArrayList<>());
            }
        }

        return lista;
    }

    private ListaObj<UsuarioDtoResposta> bubbleSortNota(ListaObj<UsuarioDtoResposta> lista) {
        ListaObj<UsuarioDtoResposta> userList = lista;
        userList = adicionaMedias(userList);
        userList = adicionaMatches(userList);

        for (int i = 0; i < userList.getTamanho(); i++) {
            for (int j = 1; j < userList.getTamanho(); j++) {
                if (userList.getElemento(j - 1).getNotaMedia() > userList.getElemento(j).getNotaMedia()) {
                    UsuarioDtoResposta aux = userList.getElemento(j);
                    userList.adicionaNoIndice(userList.getElemento(j - 1), j);
                    userList.adicionaNoIndice(aux, j - 1);
                }
            }
        }

        return userList;
    }

    private ListaObj<UsuarioDtoResposta> adicionaMedias(ListaObj<UsuarioDtoResposta> lista) {
        for (int i = 0; i < lista.getTamanho(); i++) {
            lista.getElemento(i).setNotaMedia(avaliacaoService.getMediaAvaliacaoUsuario(lista.getElemento(i).getId()));
        }

        return lista;
    }

    private UsuarioDtoResposta pesquisaBinariaPorNota(ListaObj<UsuarioDtoResposta> lista, Double nota) {

        int inicio = 0;
        int fim = lista.getTamanho() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (nota.equals(lista.getElemento(meio).getNotaMedia())) {
                UsuarioDtoResposta encontrado = lista.getElemento(meio);
                lista.removePeloIndice(meio);
                return encontrado;


            } else if (nota > lista.getElemento(meio).getNotaMedia()) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return null;
    }




}
