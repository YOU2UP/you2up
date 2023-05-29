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
import school.sptech.loginormyou2up.domain.Notificacao;
import school.sptech.loginormyou2up.domain.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.extra.ListaObj;

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
                setData(), 3.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Usuario user2 = new Usuario(2, "user2", "email2@email.com", "123",
                setData(), 3.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

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
    @DisplayName("deveRetornarUsuarioQuandoExistirUsuarioCadastradoComId")
    void deveRetornarUsuarioQuandoExistirUsuarioCadastradoComId() {
        // given
        int id = 1;

        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        Usuario usuario = new Usuario(1, "user1", "email@email.com", "123",
                setData(), 3.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Optional<Usuario> userFind = Optional.of(usuario);

        // when
        Mockito.when(usuarioRepository.findById(id)).thenReturn(userFind);

        // then
        UsuarioDtoResposta usuarioDto = usuarioService.getById(id);

        // assert
        assertDoesNotThrow(() -> {usuarioService.getById(id);});
        assertNotNull(usuarioDto);
        assertEquals(usuarioDto.getId(), id);
        assertEquals(usuarioDto.getNome(), usuario.getNome());
        assertEquals(usuarioDto.getEmail(), usuario.getEmail());
        assertEquals(usuarioDto.getDataNascimento(), usuario.getDataNascimento());
        assertEquals(usuarioDto.getNotaMedia(), usuario.getNotaMedia());
        assertEquals(usuarioDto.getEstagio(), usuario.getEstagio());
        assertEquals(usuarioDto.getSexo(), usuario.getSexo());
        assertEquals(usuarioDto.getMetaTreinos(), usuario.getMetaTreinos());
        assertEquals(usuarioDto.getNotificacoes(), usuario.getNotificacoes());
        assertEquals(usuarioDto.getTreinos(), usuario.getTreinos());
    }

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
                setData(), 3.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

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

        Usuario usuario = new Usuario(1, "user1", "email@email.com", "123", setData(), 3.0, "desc",
                "iniciante", "masculino", 10, treinos, notificacoes);

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

        Usuario usuario1 = new Usuario(2, "user2", "email@email.com", "123",
                setData(), 4.3, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Usuario usuario2 = new Usuario(2, "user2", "email@email.com", "123",
                setData(), 2.6, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Usuario usuario3 = new Usuario(3, "user2", "email@email.com", "123",
                setData(), 4.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        ListaObj<UsuarioDtoResposta> resultado = usuarioService.menorParaMaior();

        // assert
        assertDoesNotThrow(() -> {usuarioService.menorParaMaior();});
        assertNotNull(resultado);
        assertEquals(resultado.getTamanho(), size);
        assertEquals(resultado.getElemento(0).getNotaMedia(), usuario2.getNotaMedia());
        assertEquals(resultado.getElemento(1).getNotaMedia(), usuario3.getNotaMedia());
        assertEquals(resultado.getElemento(2).getNotaMedia(), usuario1.getNotaMedia());
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
    @DisplayName("deveRetornarDoisUsuariosQuandoExistirDoisUsuariosComNota4")
    void deveRetornarDoisUsuariosQuandoExistirDoisUsuariosComNota4() {
        // given
        List<Usuario> usuarios = new ArrayList<>();
        List<TreinoHasUsuario> treinos = new ArrayList<>();
        List<Notificacao> notificacoes = new ArrayList<>();

        double nota = 4.0;
        int tamanho = 2;

        Usuario user1 = new Usuario(1, "user1", "email@email.com", "123",
                setData(), nota, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Usuario user2 = new Usuario(2, "user2", "email2@email.com", "123",
                setData(), 3.0, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        Usuario user3 = new Usuario(3, "user3", "email2@email.com", "123",
                setData(), nota, "desc", "iniciante",
                "masculino", 10, treinos, notificacoes);

        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);

        // when
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // then
        ListaObj<UsuarioDtoResposta> resultado = usuarioService.buscarPorNota(nota);

        // assert
        assertDoesNotThrow(() -> {usuarioService.buscarPorNota(nota);});
        assertNotNull(resultado);
        assertEquals(resultado.getTamanho(), tamanho);

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