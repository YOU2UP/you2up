package school.sptech.loginormyou2up.service.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.domain.notificacao.Notificacao;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.dto.notificacao.NotificacaoDtoResposta;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.repository.AvaliacaoRepository;
import school.sptech.loginormyou2up.repository.MatchRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.avaliacao.AvaliacaoService;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.match.MatchService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    public LocalDate setData() {
        String nascimento = "2003/09/17";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate data = LocalDate.parse(nascimento,formatter);
        return data;
    }

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private AvaliacaoService avaliacaoMock;

    @Mock
    private MatchService matchService;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("deveRetornarDoisUsuariosQuandoDoisUsuariosCadastrados")
    void deveRetornarDoisUsuariosQuandoDoisUsuariosCadastrados() {
        // given
        int size = 2;
        List<Usuario> usuarios = new ArrayList<>();
        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario user1 = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Usuario user2 = new Usuario(2, "user2", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        usuarios.add(user1);
        usuarios.add(user2);

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        List<UsuarioDtoResposta> usuariosRespostas = usuarioService.getAll();

        // assert
        assertDoesNotThrow(() -> {usuarioService.getAll();});
        assertNotNull(usuariosRespostas);
        assertEquals(usuariosRespostas.size(), size);
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoTiverUsuarios")
    void deveRetornarResponseStatusExceptionQuandoNaoTiverUsuarios() {
        // given
        List<Usuario> usuarios = new ArrayList<>();
        int size = 0;

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.getAll();});

        // assert
        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
        assertEquals(usuarios.size(), size);
    }

/*
    @Test
    @DisplayName("deveRetornarUsuarioQuandoExistirUsuarioCadastradoComId")
    void deveRetornarUsuarioQuandoExistirUsuarioCadastradoComId() {
        // given
        int id = 1;

        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario usuario = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Optional<Usuario> userFind = Optional.of(usuario);

        List<NotificacaoDtoResposta> notificacao = new ArrayList<>();
        List<TreinoDtoJsonUsuario> treino = new ArrayList<>();
        LocalTreinoUsuario local = new LocalTreinoUsuario();
        List<MatchDtoResposta> matchesDto = new ArrayList();
        List<Match> matches = new ArrayList<>();

        UsuarioDtoResposta usuarioDto = new UsuarioDtoResposta(1,"user",
                "email@email.com", setData(), 4.5, "iniciante", 10,
                notificacao, treino, local, matchesDto);

        ListaObj<UsuarioDtoResposta> listaObj = new ListaObj<>(1);


        // when
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(userFind);

        Mockito.when(avaliacaoMock.getMediaAvaliacaoUsuario(Mockito.anyInt())).thenReturn(4.0);

        Mockito.when(matchService.getByIdUsuario(Mockito.anyInt())).thenReturn(matchesDto);

        Mockito.when(matchRepository.getAllMatchsByUsuarioId(Mockito.anyInt())).thenReturn(matches);

        Mockito.when(usuarioService.adicionaMatches(Mockito.any(UsuarioDtoResposta.class))).thenReturn(usuarioDto);

        Mockito.when(usuarioService.getById(Mockito.anyInt())).thenReturn(usuarioDto);


        // then
        UsuarioDtoResposta usuarioRetorno = usuarioService.getById(id);
        usuarioRetorno.setNotaMedia(4.0);

        // assert
        assertDoesNotThrow(() -> {usuarioService.getById(id);});
        assertNotNull(usuarioRetorno);
        assertEquals(usuarioRetorno.getId(), id);
        assertEquals(usuarioRetorno.getNome(), usuario.getNome());
        assertEquals(usuarioRetorno.getEmail(), usuario.getEmail());
        assertEquals(usuarioRetorno.getDataNascimento(), usuario.getDataNascimento());
        assertEquals(usuarioRetorno.getEstagio(), usuario.getEstagio());
        assertEquals(usuarioRetorno.getMetaTreinos(), usuario.getMetaTreinos());
    }
*/

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComId")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComId() {
        // given
        int id = 1;

        // when
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.getById(id);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("deveDeletarQuandoExistirUsuarioComId")
    void deveDeletarQuandoExistirUsuarioComId() {
        // given
        int id = 1;

        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario usuario = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Optional<Usuario> userFind = Optional.of(usuario);

        // when
        Mockito.when(usuarioRepository.findById(id)).thenReturn(userFind);

        // then
        usuarioService.deleteById(id);

        // assert
        assertDoesNotThrow(() -> {usuarioService.deleteById(id);});
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComIdDelete")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComIdDelete() {
        // given
        int id = 1;

        // when
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.deleteById(id);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComIdAtualizar")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComIdAtualizar() {
        // given
        int id = 1;

        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario usuario = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        // when
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.putById(id, usuario);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("deveOrdenarOsUsuarioComBaseNaSuaNota")
    void deveOrdenarOsUsuarioComBaseNaSuaNota(){
        // given
        int size = 3;

        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();
        List<NotificacaoDtoResposta> notificacoesDto = new ArrayList<>();
        List<TreinoDtoJsonUsuario> treinoDtoJsonUsuarios = new ArrayList<>();
        LocalTreinoUsuario localTreinoUsuario = new LocalTreinoUsuario();
        List<MatchDtoResposta> matchesDto = new ArrayList();

        Usuario usuario1 = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Usuario usuario2 = new Usuario(2, "user2", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Usuario usuario3 = new Usuario(3, "user3", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);


        UsuarioDtoResposta usuarioDto = new UsuarioDtoResposta(1,"user",
                "email@email.com", setData(), 4.5, "iniciante", 10, notificacoesDto, treinoDtoJsonUsuarios, localTreinoUsuario, matchesDto);


        ListaObj<UsuarioDtoResposta> listaObjUsuarios = new ListaObj<>(usuarios.size());
        listaObjUsuarios.adiciona(usuarioDto);


        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        Mockito.when(avaliacaoRepository.getMediaAvaliacaoUsuarioById(Mockito.anyInt())).
                thenReturn(2.5);


        Mockito.when(usuarioService.menorParaMaior()).thenReturn(listaObjUsuarios);

        // then
        ListaObj<UsuarioDtoResposta> resultado = usuarioService.menorParaMaior();

        // assert
        assertDoesNotThrow(() -> {usuarioService.menorParaMaior();});
        assertNotNull(resultado);
        assertEquals(resultado.getTamanho(), size);
        assertEquals(2.5, resultado.getElemento(0).getNotaMedia());
        assertEquals(3.5, resultado.getElemento(1).getNotaMedia());
        assertEquals(4.5, resultado.getElemento(2).getNotaMedia());
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirUsuariosNotaMenorMaior")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuariosNotaMenorMaior() {
        // given
        List<Usuario> usuarios = new ArrayList<>();
        int size = 0;

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.menorParaMaior();});

        // assert
        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
        assertEquals(usuarios.size(), size);
    }

//    @Test
//    @DisplayName("deveRetornarDoisUsuariosQuandoExistirDoisUsuariosComNota4")
//    void deveRetornarDoisUsuariosQuandoExistirDoisUsuariosComNota4() {
//        // given
//        List<Usuario> usuarios = new ArrayList<>();
//        List<TreinoHasUsuario> treinos = new ArrayList<>();
//        List<Notificacao> notificacoes = new ArrayList<>();
//
//        double nota = 4.0;
//        int tamanho = 2;
//
//        Usuario user1 = new Usuario(1, "user1", "email@email.com", "123",
//                setData(), "desc", "iniciante", 1, treinos, notificacoes);
//
//        Usuario user2 = new Usuario(2, "user2", "email@email.com", "123",
//                setData(), "desc", "iniciante", 1, treinos, notificacoes);
//
//        Usuario user3 = new Usuario(2, "user2", "email@email.com", "123",
//                setData(), "desc", "iniciante", 1, treinos, notificacoes);
//
//
//        usuarios.add(user1);
//        usuarios.add(user2);
//        usuarios.add(user3);
//
//        // when
//        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
//
//        // then
//        ListaObj<UsuarioDtoResposta> resultado = usuarioService.buscarPorNota(nota);
//
//        // assert
//        assertDoesNotThrow(() -> {usuarioService.buscarPorNota(nota);});
//        assertNotNull(resultado);
//        assertEquals(resultado.getTamanho(), tamanho);
//    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirUsuariosComNota")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuariosComNota() {
        // given
        List<Usuario> usuarios = new ArrayList<>();
        int size = 0;
        double nota = 4.0;

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {usuarioService.buscarPorNota(nota);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(usuarios.size(), size);
    }

}