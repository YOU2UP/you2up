package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuarioId;

import java.util.List;

public interface TreinoHasUsuarioRepository extends JpaRepository<TreinoHasUsuario, TreinoHasUsuarioId> {

    @Modifying
    @Query("UPDATE from TreinoHasUsuario t SET t.isRealizado = true WHERE t.treino.id = :idTreino")
    public Integer realizarTreino(int idTreino);

    @Query("SELECT t FROM TreinoHasUsuario t WHERE t.usuario.id = :idUsuario AND t.isRealizado = false")
    List<TreinoHasUsuario> encontrarTreinosPeloIdUsuario(int idUsuario);


    @Query("SELECT t FROM TreinoHasUsuario t WHERE t.usuario.id = :idUsuario")
    List<TreinoHasUsuario> contagemDeTreinosPorUsuario(int idUsuario);


}
