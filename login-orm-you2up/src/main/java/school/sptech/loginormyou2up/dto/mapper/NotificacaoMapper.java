package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.notificacao.Notificacao;
import school.sptech.loginormyou2up.dto.notificacao.NotificacaoDtoCriacao;
import school.sptech.loginormyou2up.dto.notificacao.NotificacaoDtoResposta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoMapper {

    public static NotificacaoDtoResposta convertToNotificacaoDtoResposta(Notificacao notificacao) {
        NotificacaoDtoResposta dto = new NotificacaoDtoResposta();

        dto.setId(notificacao.getIdNotificacao());
        dto.setDataHora(notificacao.getDataHora());
        dto.setConteudo(notificacao.getConteudo());

        return dto;
    }

    public static List<NotificacaoDtoResposta> convertToNotificacaoDtoResposta(List<Notificacao> notificacao) {
        List<NotificacaoDtoResposta> dtos = new ArrayList<>();
        for (Notificacao n: notificacao) {
            NotificacaoDtoResposta dto = new NotificacaoDtoResposta();

            dto.setId(n.getIdNotificacao());
            dto.setDataHora(n.getDataHora());
            dto.setConteudo(n.getConteudo());
            dtos.add(dto);
        }
        return dtos;
    }


    public static Notificacao convertToNotificacao(NotificacaoDtoCriacao notificacaoDto) {
        Notificacao notificacao = new Notificacao();

        notificacao.setConteudo(notificacaoDto.getConteudo());
        notificacao.setUsuario(notificacaoDto.getUsuario());
        notificacao.setDataHora(LocalDateTime.now());

        return notificacao;
    }
}
