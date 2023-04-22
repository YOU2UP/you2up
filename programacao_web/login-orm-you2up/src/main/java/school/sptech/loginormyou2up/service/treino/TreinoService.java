package school.sptech.loginormyou2up.service.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.domain.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.TreinoHasUsuarioId;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.repository.TreinoHasUsuarioRepository;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.service.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.service.dto.mapper.UsuarioMapper;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoResposta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoHasUsuarioRepository treinoHasUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public TreinoDtoResposta criar(TreinoDtoCriacao treinoDtoCriacao) {
        Treino treino = TreinoMapper.convertToTreino(treinoDtoCriacao);
        Treino treinoCadastrado = treinoRepository.save(treino);

        List<TreinoHasUsuario> treinoHasUsuarios = new ArrayList<>();

        for (Usuario u : treinoDtoCriacao.getUsuarios()) {
            TreinoHasUsuario treinoHasUsuario = new TreinoHasUsuario();
            treinoHasUsuario.setTreino(treinoCadastrado);
            treinoHasUsuario.setInicioTreino(treinoDtoCriacao.getInicioTreino());
            treinoHasUsuario.setQuantidadeTreinos(1);
            treinoHasUsuario.setRealizado(false);

            TreinoHasUsuarioId treinoHasUsuarioId = new TreinoHasUsuarioId();
            treinoHasUsuarioId.setTreinoId(treinoCadastrado.getId());

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(u.getId());

            if (usuarioOpt.isPresent()) {
                treinoHasUsuarioId.setUsuarioId(usuarioOpt.get().getId());
                treinoHasUsuario.setUsuario(usuarioOpt.get());
            } else {
                treinoRepository.delete(treinoCadastrado);
                throw new IllegalArgumentException();
            }

            treinoHasUsuario.setTreinoHasUsuarioId(treinoHasUsuarioId);

            treinoHasUsuarioRepository.save(treinoHasUsuario);

            treinoHasUsuarios.add(treinoHasUsuario);
        }

        return TreinoMapper.convertToTreinoDtoResposta(treino, treinoHasUsuarios);
    }


    public List<TreinoDtoResposta> findAll() {
        return TreinoMapper.convertToTreinoDtoResposta(treinoRepository.findAll());
    }


    public boolean deleteById(Integer id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            treinoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public void delete(Treino treino){
        treinoRepository.delete(treino);
    }

    public boolean treinoPossuiUsuarios(Treino treino){
        return !treino.getUsuarios().isEmpty();
    }

    public boolean treinoPossuiUsuarios(TreinoDtoResposta treino){
        return !treino.getUsuarios().isEmpty();
    }

    public void deletaTreinosSemUsuarios(List<Treino> treinos){

        for (int i = 0; i < treinos.size(); i++) {
            if (!treinoPossuiUsuarios(treinos.get(i))){
                deleteById(treinos.get(i).getId());
            }
        }
        
    }

}
