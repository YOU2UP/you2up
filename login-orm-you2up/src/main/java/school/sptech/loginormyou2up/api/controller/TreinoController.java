
package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.treino.Treino;
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
    private TreinoService treinoService;
  
    private PilhaObj<Integer> pilhaDesfazer= new PilhaObj<>(10);


    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Lista com todos os treinos " +
                    "retornada"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar a lista de treinos"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<List<TreinoDtoResposta>> getAll() {
        return ResponseEntity.status(200).body(treinoService.findAll());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Treino cadastrado " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro ao tentar " +
                    "cadastrar o treino")
    })
    public ResponseEntity<TreinoDtoResposta> post(@RequestBody @Valid TreinoDtoCriacao treino) {
        TreinoDtoResposta t = treinoService.criar(treino);
        pilhaDesfazer.push(t.getId());
        return ResponseEntity.status(201).body(t);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Treino com id escolhido " +
                    "encontrado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o treino com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<TreinoDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.status(200).body(treinoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok - Treino com id escolhido " +
                    "deletado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o treino com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        treinoService.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Treino com id escolhido " +
                    "foi atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "para atualizar o treino com o id especificado"),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<TreinoDtoResposta> putById(@PathVariable Integer id, @RequestBody Treino treino) {
        return ResponseEntity.ok().body(treinoService.putById(id,treino));
    }
  
    @DeleteMapping("/desfazer")
    public ResponseEntity<Void> desfazer() {
        if (!pilhaDesfazer.isEmpty()) {
            treinoService.deleteById(pilhaDesfazer.pop());
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

}

