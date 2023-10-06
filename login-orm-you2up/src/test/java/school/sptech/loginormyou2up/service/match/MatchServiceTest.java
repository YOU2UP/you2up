package school.sptech.loginormyou2up.service.match;

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
import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.domain.notificacao.Notificacao;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.repository.MatchRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    public LocalDateTime setDateTime() {
        String data = "2016-03-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
        return dateTime;
    }

    public LocalDate setData() {
        String nascimento = "2003/09/17";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate data = LocalDate.parse(nascimento,formatter);
        return data;
    }

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    @DisplayName("deveRetornarBadRequestQuandoDoisUsuariosIguais")
    void deveRetornarBadRequestQuandoDoisUsuariosIguais() {
        // given
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(1);

        MatchDtoCriacao dto = new MatchDtoCriacao();
        dto.setUsuario1(usuario1);
        dto.setUsuario2(usuario2);

        String mensagem = HttpStatus.BAD_REQUEST +
                " \"Não é possível criar um match com o mesmo usuário\"";

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.criarMatch(dto);});

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
        assertEquals(dto.getUsuario1().getIdUsuario(), dto.getUsuario2().getIdUsuario());
    }

    @Test
    @DisplayName("deveRetornarBadRequestQuandoExistirMatch")
    void deveRetornarBadRequestQuandoExistirMatch() {
        // given
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2);

        MatchDtoCriacao dto = new MatchDtoCriacao();
        dto.setUsuario1(usuario1);
        dto.setUsuario2(usuario2);

        String mensagem = HttpStatus.BAD_REQUEST + " \"Match já existe\"";

        // when
        Mockito.when(matchRepository.countMatches(dto.getUsuario1().getIdUsuario(),
                dto.getUsuario2().getIdUsuario())).thenReturn(1);

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.criarMatch(dto);});

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("deveRetornarNotFoundQuandoUsuarioUmNaoExiste")
    void deveRetornarNotFoundQuandoUsuarioUmNaoExiste() {
        // given
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(2);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(1);

        MatchDtoCriacao dto = new MatchDtoCriacao();
        dto.setUsuario1(usuario1);
        dto.setUsuario2(usuario2);

        String mensagem = HttpStatus.NOT_FOUND + " \"Usuário 1 não encontrado\"";

        // when
        Mockito.when(usuarioRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.criarMatch(dto);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("deveRetornarDoisMatchesQuandoExistirDoisMatches")
    void deveRetornarDoisMatchesQuandoExistirDoisMatches() {
        // given
        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario usuario1 = new Usuario(1, "user1", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        Usuario usuario2 = new Usuario(2, "user2", "email@email.com", "123",
                setData(), "desc", "iniciante", 1, treinos, notificacoes);

        List<Match> matches = new ArrayList<>();
        Match match1 = new Match();
        match1.setIdMatch(1);
        match1.setUsuario1(usuario1);
        match1.setUsuario2(usuario2);
        match1.setAtivo(true);
        match1.setDataMatch(setDateTime());
        matches.add(match1);

        Match match2 = new Match();
        match2.setIdMatch(2);
        match2.setUsuario1(usuario1);
        match2.setUsuario2(usuario2);
        match2.setAtivo(true);
        match2.setDataMatch(setDateTime());
        matches.add(match2);

        // when
        Mockito.when(matchRepository.findAll()).thenReturn(matches);

        // then
        List<MatchDtoResposta> resultado = matchService.getAll();

        // assert
        assertDoesNotThrow(() -> {matchService.getAll();});
        assertEquals(resultado.size(), matches.size());
    }

    @Test
    @DisplayName("deveRetornarNoContentQuandoNaoExistirMatches")
    void deveRetornarNoContentQuandoNaoExistirMatches() {
        // given
        List<Match> matches = new ArrayList<>();
        String mensagem = HttpStatus.NO_CONTENT + " \"Nenhum match encontrado\"";

        // when
        Mockito.when(matchRepository.findAll()).thenReturn(matches);

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.getAll();});

        // assert
        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
        assertEquals(0, matches.size());
    }

    @Test
    @DisplayName("deveDeletarMatchQuandoExistirMatchComId")
    void deveDeletarMatchQuandoExistirMatchComId() {
        // given
        Match match = new Match();
        match.setIdMatch(1);

        // when
        Mockito.when(matchRepository.findById(match.getIdMatch())).thenReturn(Optional.of(match));

        // then
        matchService.deleteById(match.getIdMatch());

        // assert
        assertDoesNotThrow(() -> {matchService.deleteById(match.getIdMatch());});
    }

    @Test
    @DisplayName("deveRetornarNotFoundQuandoNaoExistirMatchesComIdDeletar")
    void deveRetornarNotFoundQuandoNaoExistirMatchesComIdDeletar() {
        // given
        List<Match> matches = new ArrayList<>();
        String mensagem = HttpStatus.NOT_FOUND + " \"Match não encontrado\"";
        // when
        Mockito.when(matchRepository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.deleteById(1);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
        assertEquals(0, matches.size());
    }

    @Test
    @DisplayName("deveRetornarNotFoundQuandoMatchNaoExistir")
    void deveRetornarNotFoundQuandoMatchNaoExistir() {
        // given
        int id = 1;
        MatchDtoCriacao dto = new MatchDtoCriacao();
        String mensagem = HttpStatus.NOT_FOUND + " \"Match não encontrado\"";

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.putById(id, dto);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("deveRetornarBadRequestQuandoDoisUsuariosIguaisPut")
    void deveRetornarBadRequestQuandoDoisUsuariosIguaisPut() {
        // given
        // given
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(1);

        MatchDtoCriacao dto = new MatchDtoCriacao();
        dto.setUsuario1(usuario1);
        dto.setUsuario2(usuario2);

        String mensagem = HttpStatus.BAD_REQUEST +
                " \"Não é possível criar um match com o mesmo usuário\"";

        // when
        Mockito.when(matchRepository.findById(1)).thenReturn(Optional.of(new Match()));

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {matchService.putById(1, dto);});

        // assert
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    @DisplayName("deveRetornarTrueSeMatchExiste")
    void deveRetornarTrueSeMatchExiste() {
        // given
        int id1 = 1;
        int id2 = 2;

        // when
        Mockito.when(matchRepository.countMatches(id1, id2)).thenReturn(1);

        // then
        boolean existe = matchService.matchExiste(id1, id2);

        // assert
        assertTrue(existe);
    }

    @Test
    @DisplayName("deveRetornarFalseSeMatchExiste")
    void deveRetornarFalseSeMatchExiste() {
        // given
        int id1 = 1;
        int id2 = 2;

        // when
        Mockito.when(matchRepository.countMatches(id1, id2)).thenReturn(0);

        // then
        boolean existe = matchService.matchExiste(id1, id2);

        // assert
        assertFalse(existe);
    }

}