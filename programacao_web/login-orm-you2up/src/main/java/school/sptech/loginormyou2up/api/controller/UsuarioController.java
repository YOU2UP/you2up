package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.service.dto.usuario.*;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;
import school.sptech.loginormyou2up.service.dto.mapper.UsuarioMapper;
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
        if (usuarioRepository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoRespostaCadastro> post(@RequestBody @Valid UsuarioDtoCriacao usuarioDtoCriacao) {

        return ResponseEntity.status(201).body(usuarioService.criar(usuarioDtoCriacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResposta> getById(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioOpt.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
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
        if (usuarioRepository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<Usuario> lista = usuarioRepository.findAll();
        ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());
        for (int i = 0; i < lista.size(); i++) {
            listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
        }
        listaUser = bubbleSortNota(listaUser);
        return ResponseEntity.status(200).body(listaUser);

    }

    @GetMapping("/notas")
    public ResponseEntity<ListaObj<UsuarioDtoResposta>> buscarPorNota(@RequestParam Double nota) {
        if (usuarioRepository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<Usuario> lista = usuarioRepository.findAll();
        ListaObj<UsuarioDtoResposta> listaUser = new ListaObj<>(lista.size());
        for (int i = 0; i < lista.size(); i++) {
            listaUser.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
        }
        listaUser = bubbleSortNota(listaUser);

        ListaObj<UsuarioDtoResposta> encontrados = new ListaObj<>(lista.size());

        for (int i = 0; i < lista.size(); i++) {
            UsuarioDtoResposta encontrado = pesquisaBinariaPorNota(listaUser, nota);
            if (encontrado != null) {
                encontrados.adicionaNoIndice(encontrado, encontrados.getTamanho());
            }
            
        }

        ListaObj<UsuarioDtoResposta> newEncontrados = new ListaObj<>(encontrados.getTamanho());
        // nova lista para não haver retorno com vários nulls no json

        for (int i = 0; i < encontrados.getTamanho() ; i++) {
            newEncontrados.adicionaNoIndice(encontrados.getElemento(i), i);
        }

        if (newEncontrados.getTamanho() == 0) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(newEncontrados);
        }


    }

    private ListaObj<UsuarioDtoResposta> bubbleSortNota(ListaObj<UsuarioDtoResposta> lista) {
        ListaObj<UsuarioDtoResposta> userList = lista;
        for (int i = 0; i < userList.getTamanho(); i++) {
            for (int j = 1; j < userList.getTamanho(); j++) {
                if (userList.getElemento(j - 1).getNotaMedia() > userList.getElemento(j).getNotaMedia()) {
                    UsuarioDtoResposta aux = userList.getElemento(j);
                    userList.adicionaNoIndice(userList.getElemento(j - 1), j);
                    userList.adicionaNoIndice(aux, j - 1);
                }
            }
        }
        return userList;
    }

    private UsuarioDtoResposta pesquisaBinariaPorNota(ListaObj<UsuarioDtoResposta> lista, Double nota) {

        int inicio = 0;
        int fim = lista.getTamanho() - 1;

        while(inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (nota.equals(lista.getElemento(meio).getNotaMedia())) {
                UsuarioDtoResposta encontrado = lista.getElemento(meio);
                lista.removePeloIndice(meio);
                return encontrado;


                } else if (nota > lista.getElemento(meio).getNotaMedia()) {
                    inicio = meio + 1;
                } else {
                    fim = meio - 1;
                }
            }

            
        return null;
        }
    }


