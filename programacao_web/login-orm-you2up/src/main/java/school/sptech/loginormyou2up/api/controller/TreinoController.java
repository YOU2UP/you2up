package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.service.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.treino.TreinoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Treinos", description =
        "gerencia a entidade treino")
@RequestMapping("/treinos")
@RestController
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoService treinoService;


    @GetMapping
    public ResponseEntity<List<TreinoDtoResposta>> getAll() {
        if (treinoRepository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else{
            treinoService.deletaTreinosSemUsuarios(treinoRepository.findAll()   );
            if (treinoRepository.findAll().isEmpty()) {
                return ResponseEntity.status(204).build();
            }
        }

        return ResponseEntity.status(200).body(treinoService.findAll());
    }

    @PostMapping
    public ResponseEntity<TreinoDtoResposta> post(@RequestBody @Valid TreinoDtoCriacao treino) {
        return ResponseEntity.status(201).body(treinoService.criar(treino));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> getById(@PathVariable int id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            if (treinoService.treinoPossuiUsuarios(treinoOpt.get())) {
                return ResponseEntity.status(200).body(TreinoMapper.convertToTreinoDtoResposta(treinoOpt.get()));
            }else{
                treinoService.deleteById(id);
            }
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            treinoService.delete(treinoOpt.get());
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> putById(@PathVariable Integer id, @RequestBody Treino treino) {
        Optional<Treino> TreinoOpt = treinoRepository.findById(id);

        if (TreinoOpt.isPresent()) {
            treinoRepository.save(treino);
            return ResponseEntity.status(200).body(TreinoMapper.convertToTreinoDtoResposta(treino));
        }

        return ResponseEntity.status(404).build();
    }

}
