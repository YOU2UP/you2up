package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.dto.usuario.*;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Usuarios", description =
        "gerencia a entidade usuario")
@RequestMapping("/usuarios")
@RestController
public class UsuarioController<UsuarioService> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private school.sptech.loginormyou2up.service.usuario.UsuarioService usuarioService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Lista com todos usuários " +
                    "retornada"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar a lista de usuários"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<List<UsuarioDtoResposta>> getAll() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário cadastrado " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro ao tentar " +
                    "cadastrar o usuário")
    })
    public ResponseEntity<UsuarioDtoRespostaCadastro> post(@RequestBody @Valid UsuarioDtoCriacao usuarioDtoCriacao) {
        System.out.println(usuarioDtoCriacao.getEmail());
        return ResponseEntity.created(null).body(usuarioService.criar(usuarioDtoCriacao));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário com id escolhido " +
                    "encontrado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<UsuarioDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok - Usuário com id escolhido " +
                    "deletado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário com id escolhido " +
                    "foi atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "para atualizar o usuário com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<UsuarioDtoResposta> putById(@PathVariable Integer id, @RequestBody @Valid UsuarioDtoCriacao usuario) {
        Optional<Usuario> UsuarioOpt = usuarioRepository.findById(id);

        if (UsuarioOpt.isPresent()) {
            Usuario usuarioCadastrado = usuarioRepository.save(UsuarioMapper.convertToUsuario(usuario));
            return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioCadastrado));
        }

        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário autenticado " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição, " +
                    "verifique se todos os campos foram preenchidos"),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado" +
                    "com esse email"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação, credenciais" +
                    "incorretas")
    })
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @GetMapping("/ordenar-menor-maior")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Notas dos usuários " +
                    "ordenadas"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> menorParaMaior() {
        return ResponseEntity.ok().body(usuarioService.menorParaMaior());
    }

    @GetMapping("/notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuários com notas foram " +
                    "retornados"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição, " +
                    "verifique a nota digitada"),
            @ApiResponse(responseCode = "404", description = "Não existem usuários cadastrado, " +
                    "com essa nota"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> buscarPorNota(@RequestParam Double nota) {
        return ResponseEntity.ok().body(usuarioService.buscarPorNota(nota));
    }

}

