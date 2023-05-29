package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.treino.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {

}
