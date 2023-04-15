package school.sptech.loginormyou2up.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.repository.TreinoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/treinos")
@RestController
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    private List<Treino> treinos = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Treino>> getAll(){
        if(treinoRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(treinoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Treino> post(@RequestBody @Valid Treino treino){
        Treino novoTreino = treinoRepository.save(treino);
        Treino treinoNotificarUser = new Treino();
        treinoNotificarUser.notificarUsuario(novoTreino);
        return ResponseEntity.status(201).body(novoTreino);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> getById(@PathVariable int id){
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if(TreinoOpt.isPresent()){
            return ResponseEntity.status(200).body(TreinoOpt.get());
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Treino> deleteById(@PathVariable Integer id){
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if(TreinoOpt.isPresent()){
            treinoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }
    
}
