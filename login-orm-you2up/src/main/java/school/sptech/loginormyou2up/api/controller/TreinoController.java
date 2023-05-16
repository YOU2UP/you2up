package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.treino.TreinoService;

import javax.validation.Valid;
import java.util.List;

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
        return ResponseEntity.status(200).body(treinoService.findAll());
    }

    @PostMapping
    public ResponseEntity<TreinoDtoResposta> post(@RequestBody @Valid TreinoDtoCriacao treino) {
        return ResponseEntity.status(201).body(treinoService.criar(treino));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.status(200).body(treinoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        treinoService.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDtoResposta> putById(@PathVariable Integer id, @RequestBody Treino treino) {
        return ResponseEntity.ok().body(treinoService.putById(id,treino));
    }

}
