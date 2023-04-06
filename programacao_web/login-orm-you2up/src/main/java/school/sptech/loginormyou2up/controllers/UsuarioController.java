package school.sptech.loginormyou2up.controllers;

import org.apache.catalina.LifecycleState;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.classes.Usuario;
import school.sptech.loginormyou2up.repositorys.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("Usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        if(usuarioRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.status(201).body(usuarioRepository.save(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable int id){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if(usuarioOpt.isPresent()){
            return ResponseEntity.status(200).body(usuarioOpt.get());
        }

        return ResponseEntity.status(404).build();
    }

}
