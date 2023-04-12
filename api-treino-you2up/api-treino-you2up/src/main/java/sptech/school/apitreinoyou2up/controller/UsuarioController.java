package sptech.school.apitreinoyou2up.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.apitreinoyou2up.domain.Notificacao;
import sptech.school.apitreinoyou2up.domain.Usuario;
import sptech.school.apitreinoyou2up.models.mapper.UsuarioMapper;
import sptech.school.apitreinoyou2up.repository.UsuarioRepository;
import sptech.school.apitreinoyou2up.models.dto.UsuarioDto;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    UsuarioMapper usuarioMapper = new UsuarioMapper();

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuarios(@RequestBody @Valid Usuario usuario){

        LocalDateTime data = LocalDateTime.now();
        Notificacao notificacao = new Notificacao(
                usuario.getUsuario(),
                "Sistema",
                data,
                "Cadastro",
                usuario.getNome() + " obrigado por se cadastrar em nosso sistema. A partir de" +
                        "agora, você pode dar match com alguém e treinar junto."
        );

        this.usuarioRepository.save(usuario);

        NotificacaoController notificacaoController = new NotificacaoController();
        notificacaoController.exibirNotificacao(notificacao);

        notificacao.notificarUsuario(notificacao);

        return ResponseEntity.status(201).body(usuarioMapper.adicionaMapper(usuario));
    }

}
