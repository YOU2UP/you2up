package school.sptech.loginormyou2up.api.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.match.Match;
import school.sptech.loginormyou2up.dto.match.MatchDtoCriacao;
import school.sptech.loginormyou2up.dto.match.MatchDtoResposta;
import school.sptech.loginormyou2up.service.match.MatchService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<List<MatchDtoResposta>> getAll(){
        return ResponseEntity.ok(matchService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDtoResposta> getById(Integer id){
        return ResponseEntity.ok(matchService.getById(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<MatchDtoResposta>> getByIdUsuario(@PathVariable Integer id){
        return ResponseEntity.ok(matchService.getByIdUsuario(id));
    }

    @PostMapping
    public ResponseEntity<MatchDtoResposta> criarMatch(@RequestBody MatchDtoCriacao dto){
        return ResponseEntity.ok(matchService.criarMatch(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDtoResposta> atualizarMatch(MatchDtoCriacao dto, @PathVariable int id){
        return ResponseEntity.ok(matchService.putById(id, dto));
    }

    @GetMapping("/usuario/{id1}/{id2}")
    public ResponseEntity<List<MatchDtoResposta>> getMatchEntreUsuarios(@PathVariable int id1, @PathVariable int id2){
        return ResponseEntity.ok(matchService.getMatchEntreUsuarios(id1, id2));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        matchService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
