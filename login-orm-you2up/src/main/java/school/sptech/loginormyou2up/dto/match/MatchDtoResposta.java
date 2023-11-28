package school.sptech.loginormyou2up.dto.match;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRetornoDetalhes;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDtoResposta {

    @Schema(description = "Id do match", example = "5")
    private Integer id;

    @Schema(description = "Usuário 1 do match")
    private UsuarioDtoRetornoDetalhes usuario1;

    @Schema(description = "Usuário 2 do match")
    private UsuarioDtoRetornoDetalhes usuario2;

    @Schema(description = "Data do match", example = "2021-04-24T10:00:00")
    private LocalDate dataMatch;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

    public UsuarioDtoRetornoDetalhes getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioDtoRetornoDetalhes usuario1) {
        this.usuario1 = usuario1;
    }

    public UsuarioDtoRetornoDetalhes getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(UsuarioDtoRetornoDetalhes usuario2) {
        this.usuario2 = usuario2;
    }

    public LocalDate getDataMatch() {
        return dataMatch;
    }

    public void setDataMatch(LocalDate dataMatch) {
        this.dataMatch = dataMatch;
    }
}


