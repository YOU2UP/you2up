package school.sptech.loginormyou2up.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Matches", description =
        "gerencia a entidade match e a relação entre seus usuários")
@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há matches cadastrados no sistema", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<List<MatchDtoResposta>> getAll(){
        return ResponseEntity.ok(matchService.getAll());
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Match não encontrado", content = {
                    @Content( )
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<MatchDtoResposta> getById(Integer id){
        return ResponseEntity.ok(matchService.getById(id));
    }


    @GetMapping("/usuario/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content()
            })
    })
    public ResponseEntity<List<MatchDtoResposta>> getByIdUsuario(@PathVariable Integer id){
        return ResponseEntity.ok(matchService.getByIdUsuario(id));
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ok - requisição realizada com sucesso "),
            @ApiResponse(responseCode = "400", description = "Houve um erro na requisição " +
                    "ao retornar o usuário com id escolhido", content = {
                    @Content( )
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<MatchDtoResposta> criarMatch(@RequestBody MatchDtoCriacao dto){
        return ResponseEntity.ok(matchService.criarMatch(dto));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {
                    @Content( )
            }),
            @ApiResponse(responseCode = "404", description = "Match não encontrado", content = {
                    @Content( )
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<MatchDtoResposta> atualizarMatch(MatchDtoCriacao dto, @PathVariable int id){
        return ResponseEntity.ok(matchService.putById(id, dto));
    }

    @GetMapping("/usuario/{id1}/{id2}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Match entre os usuários não encontrado", content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<List<MatchDtoResposta>> getMatchEntreUsuarios(@PathVariable int id1, @PathVariable int id2){
        return ResponseEntity.ok(matchService.getMatchEntreUsuarios(id1, id2));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Match não encontrado", content = {
                    @Content( )
            }),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema", content = {
                    @Content( )
            })
    })
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        matchService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
