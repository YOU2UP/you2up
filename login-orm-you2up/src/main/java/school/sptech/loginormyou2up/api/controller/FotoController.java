package school.sptech.loginormyou2up.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.dto.FotoRespostaDto;
import school.sptech.loginormyou2up.service.foto.FotoService;

import java.util.List;

@RequestMapping("/fotos")
@RestController
public class FotoController {

    @Autowired
    private FotoService fotoService;


    @GetMapping
    public ResponseEntity<List<FotoRespostaDto>> getAll(){
        return ResponseEntity.ok(fotoService.findAll());
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


}
