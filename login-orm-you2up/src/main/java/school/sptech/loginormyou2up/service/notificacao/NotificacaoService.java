package school.sptech.loginormyou2up.service.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.loginormyou2up.domain.Notificacao;
import school.sptech.loginormyou2up.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;


    public Notificacao criar(Notificacao notificacao){
       return notificacaoRepository.save(notificacao);
    }
}
