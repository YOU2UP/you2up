package school.sptech.loginormyou2up.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.loginormyou2up.domain.foto.Foto;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto, Integer> {

    @Query("SELECT f FROM Foto f WHERE f.usuario.idUsuario = :id")
    List<Foto> findByIdUsuario(int id);

}
