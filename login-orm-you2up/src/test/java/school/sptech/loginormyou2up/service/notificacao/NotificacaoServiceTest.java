//package school.sptech.loginormyou2up.service.notificacao;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import school.sptech.loginormyou2up.domain.notificacao.Notificacao;
//import school.sptech.loginormyou2up.domain.usuario.Usuario;
//import school.sptech.loginormyou2up.repository.NotificacaoRepository;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class NotificacaoServiceTest {
//
//    public LocalDateTime setData() {
//        String data = "2016-03-04 11:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
//        return dateTime;
//    }
//
//    @Mock
//    private NotificacaoRepository notificacaoRepository;
//
//    @InjectMocks
//    private NotificacaoService notificacaoService;
//
//    @Test
//    @DisplayName("deveCriarNotificação")
//    void deveCriarNotificacao() {
//        // given
//        Notificacao notificacao = new Notificacao(1, "conteudo", setData(), new Usuario());
//
//        // when
//        Mockito.when(notificacaoRepository.save(Mockito.any(Notificacao.class))).thenReturn(notificacao);
//
//        // then
//        Notificacao resposta = notificacaoService.criar(notificacao);
//
//        // assert
//        assertNotNull(resposta);
//        assertEquals(resposta.getIdNotificacao(), notificacao.getIdNotificacao());
//        assertEquals(resposta.getConteudo(), notificacao.getConteudo());
//        assertEquals(resposta.getDataHora(), resposta.getDataHora());
//        assertEquals(resposta.getUsuario(), resposta.getUsuario());
//    }
//
//}