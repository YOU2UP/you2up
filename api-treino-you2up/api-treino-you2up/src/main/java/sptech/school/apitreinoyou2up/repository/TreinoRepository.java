package sptech.school.apitreinoyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.apitreinoyou2up.domain.Treino;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {

    List<Treino> findAllByDuracao(int duracao);

}
