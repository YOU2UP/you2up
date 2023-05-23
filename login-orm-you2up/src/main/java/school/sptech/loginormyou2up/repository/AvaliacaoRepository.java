package school.sptech.loginormyou2up.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.Avaliacao;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    @Query("SELECT a FROM Avaliacao a WHERE a.avaliador.id = :id")
    List<Avaliacao> findByIdAvaliador(int id);

    @Query("SELECT a FROM Avaliacao a WHERE a.avaliado.id = :id")
    List<Avaliacao> findByIdAvaliado(int id);

    @Query("SELECT AVG(a.   nota) from Avaliacao a WHERE a.avaliado.id = :id")
    double getMediaAvaliacaoUsuarioById(int id);

}
