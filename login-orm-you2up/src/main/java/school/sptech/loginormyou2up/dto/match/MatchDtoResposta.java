package school.sptech.loginormyou2up.dto.match;

import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioResumoDto;

import java.time.LocalDateTime;

public class MatchDtoResposta {
    private Integer id;
    private LocalDateTime dataMatch;
    private boolean isAtivo;
    private UsuarioResumoDto usuario1;
    private UsuarioResumoDto usuario2;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public LocalDateTime getDataMatch() {
      return dataMatch;
   }

   public void setDataMatch(LocalDateTime dataMatch) {
      this.dataMatch = dataMatch;
   }

   public boolean isAtivo() {
      return isAtivo;
   }

   public void setAtivo(boolean ativo) {
      isAtivo = ativo;
   }

   public UsuarioResumoDto getUsuario1() {
      return usuario1;
   }

   public void setUsuario1(UsuarioResumoDto usuario1) {
      this.usuario1 = usuario1;
   }

   public UsuarioResumoDto getUsuario2() {
      return usuario2;
   }

   public void setUsuario2(UsuarioResumoDto usuario2) {
      this.usuario2 = usuario2;
   }
}
