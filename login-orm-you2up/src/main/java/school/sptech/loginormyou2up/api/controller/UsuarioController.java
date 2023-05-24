package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.usuario.*;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.repository.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Usuarios", description =
        "gerencia a entidade usuario")
@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResposta>> getAll() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDtoRespostaCadastro> post(@RequestBody @Valid UsuarioDtoCriacao usuarioDtoCriacao) {
        return ResponseEntity.created(null).body(usuarioService.criar(usuarioDtoCriacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoResposta> putById(@PathVariable Integer id, @RequestBody @Valid UsuarioDtoCriacao usuario) {
        Optional<Usuario> UsuarioOpt = usuarioRepository.findById(id);

        if (UsuarioOpt.isPresent()) {
            Usuario usuarioCadastrado = usuarioRepository.save(UsuarioMapper.convertToUsuario(usuario));
            return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioCadastrado));
        }

        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @GetMapping("/ordenar-menor-maior")
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> menorParaMaior() {
        return ResponseEntity.ok().body(usuarioService.menorParaMaior());
    }

    @GetMapping("/nota")
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> buscarPorNota(@RequestParam Double nota) {
        return ResponseEntity.ok().body(usuarioService.buscarPorNota(nota));
    }

}


