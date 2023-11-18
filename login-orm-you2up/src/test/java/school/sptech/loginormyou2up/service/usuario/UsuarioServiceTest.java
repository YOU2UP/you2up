package school.sptech.loginormyou2up.service.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.notificacao.Notificacao;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
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

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private ListaObj listaObj;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

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