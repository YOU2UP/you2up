package school.sptech.loginormyou2up.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.Avaliacao;
import school.sptech.loginormyou2up.dto.avaliacao.AvaliacaoRespostaDto;
import school.sptech.loginormyou2up.repository.AvaliacaoRepository;
import school.sptech.loginormyou2up.service.avaliacao.AvaliacaoService;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoRespostaDto>> getAll() {
        return ResponseEntity.status(200).body(avaliacaoService.findAll());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoRespostaDto> post(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(201).body(avaliacaoService.save(avaliacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoRespostaDto> getById(@PathVariable int id) {
        return ResponseEntity.status(200).body(avaliacaoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
