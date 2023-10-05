package school.sptech.loginormyou2up.service.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.loginormyou2up.domain.avaliacao.Avaliacao;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuarioId;
import school.sptech.loginormyou2up.domain.usuario.Usuario;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoJson;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRetornoDetalhes;
import school.sptech.loginormyou2up.repository.AvaliacaoRepository;
import school.sptech.loginormyou2up.repository.TreinoHasUsuarioRepository;
import school.sptech.loginormyou2up.repository.TreinoRepository;
import school.sptech.loginormyou2up.repository.UsuarioRepository;
import school.sptech.loginormyou2up.dto.mapper.TreinoMapper;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.extra.HashObj;


import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private HashObj<Integer, TreinoDtoResposta> hashTable = new HashObj<>(); // Inicializa a tabela hash com um tamanho de 100 como exemplo

    public TreinoDtoResposta criar(TreinoDtoCriacao treinoDtoCriacao) {
        Treino treino = TreinoMapper.convertToTreino(treinoDtoCriacao);
        treino.setRealizado(false);
        Treino treinoCadastrado = treinoRepository.save(treino);

        List<TreinoHasUsuario> treinoHasUsuarios = new ArrayList<>();

        for (Usuario u : treinoDtoCriacao.getUsuarios()) {
            TreinoHasUsuario treinoHasUsuario = new TreinoHasUsuario();
            treinoHasUsuario.setTreino(treinoCadastrado);
            treinoHasUsuario.setInicioTreino(treinoDtoCriacao.getInicioTreino());

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

        if (treinoOpt.isEmpty()) {
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

    public TreinoDtoResposta putById(Integer id, Treino treino) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);

        if (treinoOpt.isPresent()) {
            return TreinoMapper.convertToTreinoDtoResposta(treinoRepository.save(treino));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    public List<TreinoDtoResposta> findTreinosByUsuarioId(int idUsuario) {
        List<TreinoHasUsuario> treinoHasUsuarios = treinoHasUsuarioRepository.contagemDeTreinosPorUsuario(idUsuario);
        List<Treino> treinos = new ArrayList<>();

        for (TreinoHasUsuario tu : treinoHasUsuarios) {
            treinos.add(treinoRepository.findById(tu.getTreino().getId()).get());
        }

        return TreinoMapper.convertToTreinoDtoResposta(treinos);
    }

    public List<TreinoDtoResposta> findTreinosEntreDoisUsuarios(int idUsuario1, int idUsuario2) {
        List<TreinoHasUsuario> treinoHasUsuarios1 = treinoHasUsuarioRepository.contagemDeTreinosPorUsuario(idUsuario1);
        List<TreinoHasUsuario> treinoHasUsuarios2 = treinoHasUsuarioRepository.contagemDeTreinosPorUsuario(idUsuario2);
        List<Treino> treinos = new ArrayList<>();

        for (TreinoHasUsuario tu1 : treinoHasUsuarios1) {
            for (TreinoHasUsuario tu2 : treinoHasUsuarios2) {
                if (tu1.getTreino().getId() == tu2.getTreino().getId()) {
                    treinos.add(treinoRepository.findById(tu1.getTreino().getId()).get());
                }
            }
        }

        return TreinoMapper.convertToTreinoDtoResposta(treinos);
    }


    public List<String> getUsuariosTreinados(int idUsuario) {
        if (usuarioRepository.findById(idUsuario).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return getUsuariosTreino(findTreinosByUsuarioId(idUsuario), idUsuario);
    }

    public List<String> getUsuariosTreino(List<TreinoDtoResposta> treinos, int idUsuario) {

        String nomeUsuario = usuarioRepository.findById(idUsuario).get().getNome();

        List<String> nomes = new ArrayList<>();

        for (TreinoDtoResposta t : treinos) {
            List<UsuarioDtoRetornoDetalhes> usuarios = t.getUsuarios();

            for (UsuarioDtoRetornoDetalhes u : usuarios) {
                if (!u.getNome().equalsIgnoreCase(nomeUsuario)) {
                    nomes.add(u.getNome());
                }
            }
        }

        return nomes;

    }


    public TreinoDtoResposta findTreinoByHash(int id) {
        TreinoDtoResposta treinoDto = hashTable.get(id);
        if (treinoDto != null) {
            return treinoDto;
        }
        Optional<Treino> treinoOptional = treinoRepository.findById(id);
        if (treinoOptional.isPresent()) {
            TreinoDtoResposta treinoResposta = TreinoMapper.convertToTreinoDtoResposta(treinoOptional.get());
            hashTable.put(id, treinoResposta);
            return treinoResposta;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treino não encontrado");
        }
    }

    public List<TreinoDtoResposta> getTreinosRealizadosNaoAvaliados(int idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);

        if (usuarioOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        List<TreinoHasUsuario> treinoHasUsuarios = treinoHasUsuarioRepository.selectTreinoFromUsuarioID(idUsuario);
        List<Treino> treinos = new ArrayList<>();
        List<Avaliacao> avaliacoes = new ArrayList<>();

        avaliacoes.addAll(avaliacaoRepository.findByIdAvaliador(idUsuario));
        avaliacoes.addAll(avaliacaoRepository.findByIdAvaliado(idUsuario));

        boolean avaliado = false;


        for (TreinoHasUsuario tu : treinoHasUsuarios) {
            for (Avaliacao a : avaliacoes) {
                if (tu.getTreino().getId() == a.getTreino().getId()) {
                    avaliado = true;
                }
            }

            if (!avaliado) {
                treinos.add(tu.getTreino());
            }

            avaliado = false;
        }


        return TreinoMapper.convertToTreinoDtoResposta(treinos);
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

    public String generateTreinosCsv(int idUsuario) {
        List<TreinoDtoResposta> treinos = findTreinosByUsuarioId(idUsuario);

        if (treinos.isEmpty()) {
            return null; // Não há treinos para este usuário
        }

        String nomeUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"))
                .getNome();

        String formattedNomeUsuario = nomeUsuario.replaceAll("\\s+", "_");

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Usuario 1,Email Usuario 1,Usuario 2,Email Usuario 2,Data Treino,Periodo Treino\n");

        for (TreinoDtoResposta treino : treinos) {
            List<UsuarioDtoRetornoDetalhes> usuarios = treino.getUsuarios();
            UsuarioDtoRetornoDetalhes usuario1 = usuarios.get(0);
            UsuarioDtoRetornoDetalhes usuario2 = usuarios.get(1);

            csvContent.append(String.format("%s,%s,%s,%s,%s,%s\n",
                    usuario1.getNome(),
                    usuario1.getEmail(),
                    usuario2.getNome(),
                    usuario2.getEmail(),
                    treino.getInicioTreino(),
                    treino.getPeriodo()));
        }

        String csvFileName = "treinos_usuario_" + formattedNomeUsuario + ".csv";

        String desktopDirectory = System.getProperty("user.home") + "/Desktop/";

        String csvFilePath = desktopDirectory + csvFileName;

        try {
            File arquivoCSV = new File(csvFilePath);
            FileWriter csvWriter = new FileWriter(arquivoCSV);
            csvWriter.write(csvContent.toString());
            csvWriter.close();

            return csvFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
