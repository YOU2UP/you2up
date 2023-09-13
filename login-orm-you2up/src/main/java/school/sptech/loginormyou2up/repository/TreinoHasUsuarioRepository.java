package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuarioId;

import java.util.List;

public interface TreinoHasUsuarioRepository extends JpaRepository<TreinoHasUsuario, TreinoHasUsuarioId> {

    @Query("SELECT t FROM TreinoHasUsuario t WHERE t.usuario.id = :idUsuario")
    List<TreinoHasUsuario> contagemDeTreinosPorUsuario(int idUsuario);

    @Query("SELECT t FROM TreinoHasUsuario t WHERE t.usuario.id = :idUsuario and t.treino.isRealizado = true")
    List<TreinoHasUsuario> selectTreinoFromUsuarioID(int idUsuario);


}
