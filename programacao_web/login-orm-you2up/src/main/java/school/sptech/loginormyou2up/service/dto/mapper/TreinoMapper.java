package school.sptech.loginormyou2up.service.dto.mapper;

import school.sptech.loginormyou2up.domain.Treino;
import school.sptech.loginormyou2up.domain.TreinoHasUsuario;
import school.sptech.loginormyou2up.domain.Usuario;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.service.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.service.dto.usuario.UsuarioDtoJson;

import java.util.ArrayList;
import java.util.List;

public class TreinoMapper {

    public static TreinoDtoJsonUsuario convertToTreinoDtoJsonUsuario(Treino treino) {
        TreinoDtoJsonUsuario dto = new TreinoDtoJsonUsuario();

        dto.setId(treino.getId());
        dto.setPeriodo(treino.getPeriodo());
        dto.setDataHora(treino.getUsuarios().get(0).getInicioTreino());
        dto.setRealizado(treino.getUsuarios().get(0).isRealizado());
        dto.setQuantidadeTreinos(treino.getUsuarios().get(0).getQuantidadeTreinos());

        return dto;
    }

    public static Treino convertToTreino(TreinoDtoCriacao treinoDtoCriacao) {
        Treino treino = new Treino();

        treino.setPeriodo(treinoDtoCriacao.getPeriodo());
        treino.setUsuarios(new ArrayList<>());

        return treino;
    }


    public static TreinoDtoResposta convertToTreinoDtoResposta(Treino treino) {
        TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

        treinoDtoResposta.setId(treino.getId());
        treinoDtoResposta.setPeriodo(treino.getPeriodo());
        treinoDtoResposta.setInicioTreino(treino.getUsuarios().get(0).getInicioTreino());

        List<TreinoHasUsuario> lista = treino.getUsuarios();
        List<UsuarioDtoJson> listaUsuarios = new ArrayList<>();

        for (TreinoHasUsuario tu : lista) {
            listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoJson(tu.getUsuario() ));
        }

        treinoDtoResposta.setUsuarios(listaUsuarios);

        return treinoDtoResposta;
    }

    public static TreinoDtoResposta convertToTreinoDtoResposta(Treino treino, List<TreinoHasUsuario> treinoHasUsuarios) {
        TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

        treinoDtoResposta.setId(treino.getId());
        treinoDtoResposta.setPeriodo(treino.getPeriodo());
        treinoDtoResposta.setInicioTreino(treinoHasUsuarios.get(0).getInicioTreino());

        List<UsuarioDtoJson> listaUsuarios = new ArrayList<>();

        for (TreinoHasUsuario tu : treinoHasUsuarios) {
            listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoJson(tu.getUsuario()));
        }

        treinoDtoResposta.setUsuarios(listaUsuarios);

        return treinoDtoResposta;
    }

    public static List<TreinoDtoResposta> convertToTreinoDtoResposta(List<Treino> treinos) {
        List<TreinoDtoResposta> listaRetorno = new ArrayList<>();

        for (int i = 0; i < treinos.size(); i++) {

            TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

            treinoDtoResposta.setId(treinos.get(i).getId());
            treinoDtoResposta.setPeriodo(treinos.get(i).getPeriodo());

            treinoDtoResposta.setInicioTreino(treinos.get(i).getUsuarios().get(0).getInicioTreino());

            List<UsuarioDtoJson> listaUsuarios = new ArrayList<>();

            for (TreinoHasUsuario tu: treinos.get(i).getUsuarios()) {
                listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoJson(tu.getUsuario()));
            }

            treinoDtoResposta.setUsuarios(listaUsuarios);

            listaRetorno.add(treinoDtoResposta);
        }



        return listaRetorno;
    }

}
