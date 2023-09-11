package school.sptech.loginormyou2up.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.loginormyou2up.domain.treino.Treino;

import java.util.List;
import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Integer> {
}
