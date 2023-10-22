package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.dto.treino.QuantidadeTreinosPorDiaSemanaDto;
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
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Lista com todos usuários " +
                    "retornada"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar a lista de usuários", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<List<UsuarioDtoResposta>> getAll() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    @PostMapping("/cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário cadastrado " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro ao tentar " +
                    "cadastrar o usuário", content = {
                    @Content()
            })
    })
    public ResponseEntity<UsuarioDtoRespostaCadastro> post(@RequestBody @Valid UsuarioDtoCriacao usuarioDtoCriacao) {
        System.out.println(usuarioDtoCriacao.getEmail());
        return ResponseEntity.created(null).body(usuarioService.criar(usuarioDtoCriacao));
    }

    @PostMapping("/imagem-perfil/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - foto de perfil alterada " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro ao tentar " +
                    "alterar a foto de perfil", content = {
                    @Content()
            })
    })
    public ResponseEntity<String> postImagemPerfil(@PathVariable int id, @RequestParam String link, @RequestParam String token) {
        System.out.println(link);
        return ResponseEntity.ok().body(usuarioService.postFotoPerfil(id, link, token));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário com id escolhido " +
                    "encontrado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<UsuarioDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok - Usuário com id escolhido " +
                    "deletado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
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
                    "para atualizar o usuário com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
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
                    "verifique se todos os campos foram preenchidos", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado " +
                    "com esse email", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação, credenciais " +
                    "incorretas", content = {
                    @Content()
            })
    })
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @GetMapping("/ordenar-menor-maior")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Notas dos usuários " +
                    "ordenadas"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> menorParaMaior() {
        return ResponseEntity.ok().body(usuarioService.menorParaMaior());
    }

    @GetMapping("/notas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuários com notas foram " +
                    "retornados"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição, " +
                    "verifique a nota digitada", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existem usuários cadastrados   " +
                    "com essa nota", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> buscarPorNota(@RequestParam Double nota) {
        return ResponseEntity.ok().body(usuarioService.buscarPorNota(nota));
    }


    @GetMapping("/quantidade-treinos-semana/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Quantidae de treinos + " +
                    "por dia da semana retornados"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição, " +
                    "verifique o corpo da requisição", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existem usuários cadastrado, " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema, é necessário passar um token válido " +
                    "no corpo da requisição", content = {
                    @Content()
            })
    })
    public ResponseEntity<QuantidadeTreinosPorDiaSemanaDto> getQuantidadeTreinosSemanaById(@PathVariable int id) {
        return ResponseEntity.ok().body(usuarioService.getQuantidadeTreinosPorDiaSemana(id));
    }

    @GetMapping("recursivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Lista com todos usuários " +
                    "retornada"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar a lista de usuários", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public void getAllRecursivo() {
        List<UsuarioDtoResposta> usersList = usuarioService.getAll();
        usuarioService.exibeRecursivo(usersList, 0);
    }

    @GetMapping("recursivo/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário com id escolhido " +
                    "encontrado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "204", description = "Não existe usuário cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<UsuarioDtoResposta> getByIdRecursivo(@PathVariable Integer id) {
        long totalUser = usuarioRepository.count();
        System.out.println(totalUser);
        return usuarioService.buscarPorIdRecursivo(0, totalUser, id);
    }


    @PutMapping("/meta-treinos/{id}/{meta}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Usuário com id escolhido " +
                    "foi atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "para atualizar o usuário com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<UsuarioResumoDto> editaMetaTreinos(@PathVariable int id, @PathVariable int meta) {
        return ResponseEntity.ok().body(usuarioService.editaMetaTreinos(id, meta));
    }

}


