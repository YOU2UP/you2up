package sptech.school.apitreinoyou2up.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.apitreinoyou2up.domain.Treino;
import sptech.school.apitreinoyou2up.domain.TreinoAcademia;
import sptech.school.apitreinoyou2up.domain.TreinoArLivre;
import sptech.school.apitreinoyou2up.repository.TreinoRepository;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoRepository treinoRepository;

    public TreinoController() {
    }

    @PostMapping("/ar-livre")
    public ResponseEntity<TreinoArLivre> cadastrarTreinoArLivre(
            @RequestBody
            @Valid TreinoArLivre treino) {
        TreinoArLivre treinoArLivre = this.treinoRepository.save(treino);
        return ResponseEntity.status(201).body(treinoArLivre);
    }

    @PostMapping("/academia")
    public ResponseEntity<TreinoAcademia> cadastrarTreinoAcademia(
            @RequestBody
            @Valid TreinoAcademia treino){
        TreinoAcademia treinoAcademia = this.treinoRepository.save(treino);
        return ResponseEntity.status(201).body(treinoAcademia);
    }

    @GetMapping()
    public ResponseEntity<List<Treino>> mostrarTreinos(){
        List<Treino> treinos = this.treinoRepository.findAll();
        if (treinos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(treinos);
        }
    }

    @GetMapping("/calorias")
    public ResponseEntity<Double> gastoCaloricoTotal(){
        List<Treino> gasto = this.treinoRepository.findAll();
        if (gasto.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            double total = 0.0;
            for (Treino g: gasto) {
                total = total + g.getGastoCalorico();
            }
            return ResponseEntity.status(200).body(total);
        }
    }

    @GetMapping("/{duracao}")
    public ResponseEntity<List<Treino>> mostrarTreinosDuracao(@PathVariable int duracao){
        List<Treino> treinos = this.treinoRepository.findAllByDuracao(duracao);
        if (treinos.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(treinos);
        }
    }

}
