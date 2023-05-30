package school.sptech.loginormyou2up.api.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.loginormyou2up.domain.avaliacao.Avaliacao;
import school.sptech.loginormyou2up.dto.avaliacao.AvaliacaoRespostaDto;
import school.sptech.loginormyou2up.service.avaliacao.AvaliacaoService;

import java.util.List;

@Tag(name = "Avaliacoes", description =
        "gerencia a entidade avaliacao ")
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há avaliacoes cadastrados no sistema"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<List<AvaliacaoRespostaDto>> getAll() {
        return ResponseEntity.status(200).body(avaliacaoService.findAll());
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliacao criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    })
    public ResponseEntity<AvaliacaoRespostaDto> post(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(201).body(avaliacaoService.save(avaliacao));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliação não encontrada"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<AvaliacaoRespostaDto> getById(@PathVariable int id) {
        return ResponseEntity.status(200).body(avaliacaoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliação não encontrada"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/avaliador/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliador não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<List<AvaliacaoRespostaDto>> findByIdAvaliador(@PathVariable int id){
        return ResponseEntity.ok().body(avaliacaoService.findByIdAvaliador(id));
    }

    @GetMapping("/avaliado/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliado não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<List<AvaliacaoRespostaDto>> findByIdAvaliado(@PathVariable int id){
        return ResponseEntity.ok().body(avaliacaoService.findByIdAvaliado(id));
    }


    @GetMapping("/media/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - requisição realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "avaliado não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação. Parece que " +
                    "você não está autenticado no sistema")
    } )
    public ResponseEntity<Double> getMediaByIdAvalido(@PathVariable int id){
        return ResponseEntity.ok().body(avaliacaoService.getMediaAvaliacaoUsuario(id));
    }

}
