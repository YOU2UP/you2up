package school.sptech.loginormyou2up.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.service.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoResposta;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RequestMapping("/treinos")
@RestController
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;


    @GetMapping
    public ResponseEntity<List<TreinoDtoResposta>> getAll(){
        if(treinoRepository.findAll().isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(TreinoMapper.convertToTreinoDtoResposta(treinoRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<TreinoDtoResposta> post(@RequestBody @Valid Treino treino){
        treino.notificarUsuario();
        return ResponseEntity.status(201).body(TreinoMapper.convertToTreinoDtoResposta(treinoRepository.save(treino)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> getById(@PathVariable int id){
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if(TreinoOpt.isPresent()){
            return ResponseEntity.status(200).body(TreinoMapper.convertToTreinoDtoResposta(TreinoOpt.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if(TreinoOpt.isPresent()){
            treinoRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> putById(@PathVariable Integer id, @RequestBody Treino treino){
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if(TreinoOpt.isPresent()){
            treinoRepository.save(treino);
            return ResponseEntity.status(200).body(TreinoMapper.convertToTreinoDtoResposta(treino));
        }

        return ResponseEntity.status(404).build();
    }

    
}
