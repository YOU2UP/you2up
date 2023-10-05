
package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.extra.FilaObj;

import school.sptech.loginormyou2up.service.extra.PilhaObj;
import school.sptech.loginormyou2up.service.treino.TreinoService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Tag(name = "Treinos", description =
        "gerencia a entidade treino")
@RequestMapping("/treinos")
@RestController
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    private PilhaObj<Integer> pilhaDesfazer = new PilhaObj<>(10);

    private FilaObj<Integer> naoRealizados = new FilaObj<>(90);


    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Lista com todos os treinos " +
                    "retornada"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar a lista de treinos", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<List<TreinoDtoResposta>> getAll() {
        return ResponseEntity.status(200).body(treinoService.findAll());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Treino cadastrado " +
                    "com sucesso"),
            @ApiResponse(responseCode = "400", description = "Houve um erro ao tentar " +
                    "cadastrar o treino", content = {
                    @Content()
            })
    })
    public ResponseEntity<TreinoDtoResposta> post(@RequestBody @Valid TreinoDtoCriacao treino) {
        TreinoDtoResposta t = treinoService.criar(treino);
        pilhaDesfazer.push(t.getId());
        naoRealizados.insert(t.getId());
        return ResponseEntity.status(201).body(t);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Treino com id escolhido " +
                    "encontrado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o treino com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<TreinoDtoResposta> getById(@PathVariable int id) {
        return ResponseEntity.status(200).body(treinoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ok - Treino com id escolhido " +
                    "deletado"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o treino com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
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
                    "para atualizar o treino com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado" +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<TreinoDtoResposta> putById(@PathVariable Integer id, @RequestBody Treino treino) {
        return ResponseEntity.ok().body(treinoService.putById(id, treino));
    }

    @DeleteMapping("/desfazer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - última ação desfeita"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<Void> desfazer() {
        if (!pilhaDesfazer.isEmpty()) {
            treinoService.deleteById(pilhaDesfazer.pop());
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - contagem de treinos realizada com sucessoo"),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "para atualizar o treino com o id especificado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "404", description = "Não existe treino cadastrado " +
                    "com esse id", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    @GetMapping("/contagem-treinos/{id}")
    public ResponseEntity<List<String>> getNomesParceirosDeTreino(@PathVariable int id) {
        return ResponseEntity.ok().body(treinoService.getUsuariosTreinados(id));
    }


    @GetMapping("/findTreinoByHash/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Treino encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado")
    })
    public ResponseEntity<TreinoDtoResposta> getTreinoByHash(@PathVariable int id) {
        TreinoDtoResposta treinoDto = treinoService.findTreinoByHash(id);

        if (treinoDto != null) {
            return ResponseEntity.status(200).body(treinoDto);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/nao-avaliados/{id}")
    public ResponseEntity<List<TreinoDtoResposta>> getTreinosRealizadosNaoAvaliados(@PathVariable int id) {
        return ResponseEntity.ok().body(treinoService.getTreinosRealizadosNaoAvaliados(id));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<TreinoDtoResposta>> getTreinosByUsuarioId(@PathVariable int id) {
        return ResponseEntity.ok().body(treinoService.findTreinosByUsuarioId(id));
    }

    @GetMapping("/usuario/{id1}/{id2}")
    public ResponseEntity<List<TreinoDtoResposta>> getTreinosEntreDoisUsuarios(@PathVariable int id1, @PathVariable int id2) {
        return ResponseEntity.ok().body(treinoService.findTreinosEntreDoisUsuarios(id1, id2));
    }


    @GetMapping("/listTreinosCsv/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo CSV gerado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Treinos não encontrados para o usuário")
    })
    public ResponseEntity<String> listTreinosCsv(@PathVariable int id) {
        String csvFileName = treinoService.generateTreinosCsv(id);

        if (csvFileName == null) {
            return ResponseEntity.status(404).body("Treinos não encontrados para o usuário");
        }

        return ResponseEntity.status(200).body("Arquivo CSV gerado com sucesso: " + csvFileName);
    }
}

