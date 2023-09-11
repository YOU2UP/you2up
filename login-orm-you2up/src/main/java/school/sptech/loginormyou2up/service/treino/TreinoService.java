package school.sptech.loginormyou2up.service.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuarioId;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoJson;
import school.sptech.loginormyou2up.repository.TreinoHasUsuarioRepository;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.extra.HashObj;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private TreinoHasUsuarioRepository treinoHasUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private HashObj<Integer, TreinoDtoResposta> hashTable = new HashObj<>(); // Inicializa a tabela hash com um tamanho de 100 como exemplo

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
        if (treinoRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            deletaTreinosSemUsuarios(treinoRepository.findAll());
            if (treinoRepository.findAll().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
        }

        return TreinoMapper.convertToTreinoDtoResposta(treinoRepository.findAll());
    }


    public TreinoDtoResposta findById(Integer id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            if (treinoPossuiUsuarios(treinoOpt.get())) {
                return TreinoMapper.convertToTreinoDtoResposta(treinoOpt.get());
            } else {
                deleteById(id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public void deleteById(Integer id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            treinoRepository.deleteById(id);
            return;
        }

    }

    public void deletaTreinosSemUsuarios(List<Treino> treinos) {

        for (int i = 0; i < treinos.size(); i++) {
            if (!treinoPossuiUsuarios(treinos.get(i))) {
                deleteById(treinos.get(i).getId());
            }
        }

    }

    public TreinoDtoResposta putById(Integer id, Treino treino){
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            return TreinoMapper.convertToTreinoDtoResposta(treinoRepository.save(treino));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public List<Integer> buscarIdsTreino(int idUsuario) {
        List<TreinoHasUsuario> t = treinoHasUsuarioRepository.encontrarTreinosPeloIdUsuario(idUsuario);
        List<Integer> ids = new ArrayList<>();
        if (t.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < t.size(); i++) {
                ids.add(t.get(i).getTreino().getId());
            }
            return ids;
        }
    }

    @Transactional
    public int realizaTreinoNaFila(int idTreino) {
        Integer rowAffected = treinoHasUsuarioRepository.realizarTreino(idTreino);
        return rowAffected;   
    }

    public List<TreinoDtoResposta> findTreinosByUsuarioId(int idUsuario) {
        List<TreinoHasUsuario> treinoHasUsuarios = treinoHasUsuarioRepository.contagemDeTreinosPorUsuario(idUsuario);
        List<Treino> treinos = new ArrayList<>();

        for (TreinoHasUsuario tu: treinoHasUsuarios) {
            treinos.add(treinoRepository.findById(tu.getTreino().getId()).get());
        }

        return TreinoMapper.convertToTreinoDtoResposta(treinos);
    }

    public List<String> getUsuariosTreinados(int idUsuario){
        if(usuarioRepository.findById(idUsuario).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return getUsuariosTreino(findTreinosByUsuarioId(idUsuario), idUsuario);
    }

    public List<String> getUsuariosTreino(List<TreinoDtoResposta> treinos, int idUsuario){

        String nomeUsuario = usuarioRepository.findById(idUsuario).get().getNome();

        List<String> nomes = new ArrayList<>();

        for (TreinoDtoResposta t: treinos ) {
            List<UsuarioDtoJson> usuarios = t.getUsuarios();

            for (UsuarioDtoJson u: usuarios) {
                if (!u.getNome().equalsIgnoreCase(nomeUsuario)){
                    nomes.add(u.getNome());
                }
            }
        }

        return nomes;

    }

    public TreinoDtoResposta findTreinoByHash(int id) {
        // Verificar se o treino está na tabela hash
        TreinoDtoResposta treinoDto = hashTable.get(id);

        if (treinoDto != null) {
            // Se encontrado na tabela hash, retornar imediatamente
            return treinoDto;
        }

        // Se não encontrado na tabela hash, buscar no banco de dados
        Optional<Treino> treinoOptional = treinoRepository.findById(id);

        if (treinoOptional.isPresent()) {
            // Converter o treino para um DTO de resposta
            TreinoDtoResposta treinoResposta = TreinoMapper.convertToTreinoDtoResposta(treinoOptional.get());

            // Adicionar o treino à tabela hash
            hashTable.put(id, treinoResposta);

            return treinoResposta;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treino não encontrado");
        }
    }



    public void delete(Treino treino) {
        treinoRepository.delete(treino);
    }

    public boolean treinoPossuiUsuarios(Treino treino) {
        return !treino.getUsuarios().isEmpty();
    }

    public boolean treinoPossuiUsuarios(TreinoDtoResposta treino) {
        return !treino.getUsuarios().isEmpty();
    }


}
