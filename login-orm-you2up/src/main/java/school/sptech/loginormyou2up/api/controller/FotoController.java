package school.sptech.loginormyou2up.api.controller;

import org.springdoc.core.SpringDocUIConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.foto.Foto;
import school.sptech.loginormyou2up.domain.foto.FotoPerfil;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.FotoRespostaDto;
import school.sptech.loginormyou2up.repository.FotoPerfilRepository;
import school.sptech.loginormyou2up.service.foto.FotoService;

import java.util.List;

@RequestMapping("/fotos")
@RestController
public class FotoController {

    @Autowired
    private FotoService fotoService;


    @GetMapping
    public ResponseEntity<List<FotoRespostaDto>> getAll(){
        return ResponseEntity.ok(fotoService.findAllFotos());
    }

    @GetMapping("/perfil")
    public ResponseEntity<List<FotoRespostaDto>> getAllFotoPerfil(){
        return ResponseEntity.ok(fotoService.findAllFotoPerfil());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoRespostaDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(fotoService.findById(id));
    }

    @GetMapping("/feed/{idUsuario}")
    public ResponseEntity<List<FotoRespostaDto>> getFeedUsuario(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(fotoService.getFeedFotos(idUsuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        fotoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/perfil/{id}")
    public ResponseEntity<Void> deleteFotoPerfilById(@PathVariable Integer id){
        fotoService.deleteFotoPerfilById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/perfil/usuario/{idUsuario}")
    public ResponseEntity<Void> deleteFotoPerfilByIdUsuario(@PathVariable Integer idUsuario){
        fotoService.deleteFotoPerfilByIdUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfil/usuario/{idUsuario}")
    public ResponseEntity<FotoRespostaDto> getFotoPerfilByIdUsuario(@PathVariable Integer idUsuario){
        return ResponseEntity.ok(fotoService.getFotoPerfilUsuario(idUsuario));
    }


    @PostMapping
    public ResponseEntity<FotoRespostaDto> save(@RequestBody Foto foto){
        return ResponseEntity.ok(fotoService.save(foto));
    }

    @PostMapping("/perfil")
    public ResponseEntity<FotoRespostaDto> saveFotoPerfil(@RequestBody FotoPerfil foto){
        return ResponseEntity.ok(fotoService.saveFotoPerfil(foto));
    }

    @PutMapping("/perfil/{idUsuario}")
    public ResponseEntity<FotoRespostaDto> updateFotoPerfil(@PathVariable int idUsuario, @RequestBody FotoPerfil foto){
        return ResponseEntity.ok(fotoService.updateFotoPerfil(idUsuario, foto));
    }


}
