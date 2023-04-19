package school.sptech.loginormyou2up.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.service.extra.ListaObj;
import school.sptech.loginormyou2up.service.usuario.UsuarioService;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoCriacao;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoResposta;
import school.sptech.loginormyou2up.service.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioLoginDto;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioTokenDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDtoResposta>> getAll(){
        if(usuarioRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoResposta> post(@RequestBody @Valid UsuarioDtoCriacao usuarioDtoCriacao){

        return ResponseEntity.status(201).body(usuarioService.criar(usuarioDtoCriacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoResposta> getById(@PathVariable int id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if(usuarioOpt.isPresent()){
            return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioOpt.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if(usuarioOpt.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoResposta> putById(@PathVariable Integer id, @RequestBody @Valid UsuarioDtoCriacao usuario){
        Optional<Usuario> UsuarioOpt = usuarioRepository.findById(id);

        if(UsuarioOpt.isPresent()){
            Usuario usuarioCadastrado = usuarioRepository.save(UsuarioMapper.convertToUsuario(usuario));
            return ResponseEntity.status(200).body(UsuarioMapper.convertToDtoResposta(usuarioCadastrado));
        }

        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

//    @GetMapping("/ordenar-menor-maior")
//    public ResponseEntity<ListaObj<UsuarioDtoResposta>> menorParaMaior() {
//        if(usuarioRepository.findAll().isEmpty()){
//            return ResponseEntity.status(204).build();
//        }
//        List<Usuario> lista = usuarioRepository.findAll();
//        ListaObj<UsuarioDtoResposta> listaObj = new ListaObj<>(lista.size());
//        for (int i = 0; i < listaObj.getTamanho(); i++) {
//            listaObj.adicionaNoIndice(UsuarioMapper.convertToDtoResposta(lista.get(i)), i);
//        }
//
//    }
}
