package school.sptech.loginormyou2up.service.dto.mapper;

import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoResposta;

import java.util.ArrayList;
import java.util.List;

public class TreinoMapper {

    public static TreinoDtoJsonUsuario convertToTreinoDtoJsonUsuario(Treino treino) {
        TreinoDtoJsonUsuario dto = new TreinoDtoJsonUsuario();

        dto.setId(treino.getId());
        dto.setPeriodo(treino.getPeriodo());
        dto.setPreferencia(treino.getPreferencia());

        return dto;
    }

    public static Treino convertToTreino(TreinoDtoCriacao treinoDtoCriacao) {
        Treino treino = new Treino();

        treino.setPeriodo(treinoDtoCriacao.getPeriodo());
        treino.setPreferencia(treinoDtoCriacao.getPreferencia());
        treino.setInicioTreino(treinoDtoCriacao.getInicioTreino());
        treino.setUsuarios(treinoDtoCriacao.getUsuarios());

        return treino;
    }


    public static TreinoDtoResposta convertToTreinoDtoResposta(Treino treino) {
        TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

        treinoDtoResposta.setId(treino.getId());
        treinoDtoResposta.setPeriodo(treino.getPeriodo());
        treinoDtoResposta.setPreferencia(treino.getPreferencia());
        treinoDtoResposta.setInicioTreino(treino.getInicioTreino());
        treinoDtoResposta.setUsuarios(treino.getUsuarios());

        return treinoDtoResposta;
    }

    public static List<TreinoDtoResposta> convertToTreinoDtoResposta(List<Treino> treinos) {
        List<TreinoDtoResposta> listaRetorno = new ArrayList<>();

        for (Treino treino : treinos) {
            TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

            treinoDtoResposta.setId(treino.getId());
            treinoDtoResposta.setPeriodo(treino.getPeriodo());
            treinoDtoResposta.setPreferencia(treino.getPreferencia());
            treinoDtoResposta.setInicioTreino(treino.getInicioTreino());
            treinoDtoResposta.setUsuarios(treino.getUsuarios());

            listaRetorno.add(treinoDtoResposta);

        }

        return listaRetorno;
    }

}
