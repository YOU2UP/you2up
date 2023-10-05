package school.sptech.loginormyou2up.service.treino;

import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuarioId;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoJson;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRetornoDetalhes;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TreinoServiceTest {

    public LocalDateTime setData() {
        String data = "2016-03-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
        return dateTime;
    }

    @Mock
    private TreinoRepository treinoRepository;

    @InjectMocks
    private TreinoService treinoService;

    @Test
    @DisplayName("deveRetornarDoisUsuariosQuandoDoisUsuariosCadastrados")
    void deveRetornarDoisUsuariosQuandoDoisUsuariosCadastrados() {
        // given
        int size = 2;
        List<Treino> treinos = new ArrayList<>();

        TreinoHasUsuarioId id = new TreinoHasUsuarioId(1,1);

        TreinoHasUsuario user1 = new TreinoHasUsuario(id, new Treino(), new Usuario(), setData(),
                1, true);
        TreinoHasUsuario user2 = new TreinoHasUsuario(id, new Treino(), new Usuario(), setData(),
                1, true);

        List<TreinoHasUsuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);

        Treino treino1 = new Treino(1, "manha", usuarios);
        Treino treino2 = new Treino(1, "manha", usuarios);

        treinos.add(treino1);
        treinos.add(treino2);

        Mockito.when(treinoRepository.findAll()).thenReturn(treinos);

        // when
        List<TreinoDtoResposta> treinoDtoRespostas = treinoService.findAll();

        // then
        assertDoesNotThrow(() -> {treinoService.findAll();});
        assertNotNull(treinoDtoRespostas);
        assertEquals(treinoDtoRespostas.size(), size);
    }


    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoTiverTreinos")
    void deveRetornarResponseStatusExceptionQuandoNaoTiverTreinos() {
        // given
        List<TreinoDtoResposta> treinos = new ArrayList<>();
        int size = 0;

        // when
        Mockito.when(treinoRepository.findAll()).thenReturn(new ArrayList<>());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {treinoService.findAll();});

        // assert
        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
        assertEquals(treinos.size(), size);
    }

    @Test
    @DisplayName("deveRetornarTreinoQuandoExistirTreinoCadastradoComId")
    void deveRetornarTreinoQuandoExistirTreinoCadastradoComId() {
        // given
        int id = 1;

        List<Treino> treinos = new ArrayList<>();

        TreinoHasUsuarioId idUser = new TreinoHasUsuarioId(1,1);

        TreinoHasUsuario user1 = new TreinoHasUsuario(idUser, new Treino(), new Usuario(),
                setData(), 1, true);
        TreinoHasUsuario user2 = new TreinoHasUsuario(idUser, new Treino(), new Usuario(),
                setData(), 1, true);

        List<TreinoHasUsuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);

        Treino treino = new Treino(1, "manha", usuarios);

        // when
        Mockito.when(treinoRepository.findById(id)).thenReturn(Optional.of(treino));

        // then
        TreinoDtoResposta treinoComId = treinoService.findById(id);

        // assert
        assertDoesNotThrow(() -> {treinoService.findById(id);});
        assertNotNull(treinoComId);
        assertEquals(treinoComId.getId(), id);
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirTreinoComId")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirTreinoComId() {
        // given
        int id = 1;

        // when
        Mockito.when(treinoRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {treinoService.findById(id);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("deveDeletarQuandoExistirTreinoComId")
    void deveDeletarQuandoExistirTreinoComId() {
        // given
        int id = 1;

        List<Treino> treinos = new ArrayList<>();

        TreinoHasUsuarioId idUser = new TreinoHasUsuarioId(1,1);

        TreinoHasUsuario user1 = new TreinoHasUsuario(idUser, new Treino(), new Usuario(),
                setData(), 1, true);
        TreinoHasUsuario user2 = new TreinoHasUsuario(idUser, new Treino(), new Usuario(),
                setData(), 1, true);

        List<TreinoHasUsuario> usuarios = new ArrayList<>();
        usuarios.add(user1);
        usuarios.add(user2);

        Treino treino = new Treino(1, "manha", usuarios);

        // when
        Mockito.when(treinoRepository.findById(id)).thenReturn(Optional.of(treino));

        // then
        treinoService.deleteById(id);

        // assert
        assertDoesNotThrow(() -> {treinoService.deleteById(id);});
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirTreinoComIdDelete")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirUsuarioComIdDelete() {
        // given
        int id = 1;

        // when
        Mockito.when(treinoRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {treinoService.deleteById(id);});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("deveRetornarResponseStatusExceptionQuandoNaoExistirTreinoComIdAtualiza")
    void deveRetornarResponseStatusExceptionQuandoNaoExistirTreinoComIdAtualiza() {
        // given
        int id = 1;

        // when
        Mockito.when(treinoRepository.findById(id)).thenReturn(Optional.empty());

        // then
        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> {treinoService.putById(id, new Treino());});

        // assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    @DisplayName("retornaTrueSeTreinoPossuiUsuarios")
    void retornaTrueSeTreinoPossuiUsuarios() {
        // given
        Treino treino = new Treino();
        List<TreinoHasUsuario> usuarios = new ArrayList<>();
        usuarios.add(new TreinoHasUsuario());
        usuarios.add(new TreinoHasUsuario());

        treino.setUsuarios(usuarios);

        // assert
        assertTrue(treinoService.treinoPossuiUsuarios(treino));
    }

    @Test
    @DisplayName("retornaFalseSeTreinoNaoPossuiUsuarios")
    void retornaFalseSeTreinoNaoPossuiUsuarios() {
        // given
        Treino treino = new Treino();
        List<TreinoHasUsuario> usuarios = new ArrayList<>();
        treino.setUsuarios(usuarios);

        // assert
        assertFalse(treinoService.treinoPossuiUsuarios(treino));
    }

    @Test
    @DisplayName("retornaTrueSeTreinoDtoRespostaPossuiUsuarios")
    void retornaTrueSeTreinoDtoRespostaPossuiUsuarios() {
        // given
        TreinoDtoResposta treino = new TreinoDtoResposta();
        List<UsuarioDtoRetornoDetalhes> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioDtoRetornoDetalhes());
        usuarios.add(new UsuarioDtoRetornoDetalhes());
        treino.setUsuarios(usuarios);

        // assert
        assertTrue(treinoService.treinoPossuiUsuarios(treino));
    }

    @Test
    @DisplayName("retornaFalseSeTreinoDtoRespostaNaoPossuiUsuarios")
    void retornaFalseSeTreinoDtoRespostaNaoPossuiUsuarios() {
        // given
        TreinoDtoResposta treino = new TreinoDtoResposta();
        List<UsuarioDtoRetornoDetalhes> usuarios = new ArrayList<>();
        treino.setUsuarios(usuarios);

        // assert
        assertFalse(treinoService.treinoPossuiUsuarios(treino));
    }

}