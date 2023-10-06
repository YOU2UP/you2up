package school.sptech.loginormyou2up.dto.mapper;

import school.sptech.loginormyou2up.domain.localTreino.LocalTreinoUsuario;
import school.sptech.loginormyou2up.domain.treino.Treino;
import school.sptech.loginormyou2up.domain.treinoHasUsuario.TreinoHasUsuario;
import school.sptech.loginormyou2up.dto.treino.LocalTreinoCriacaoDto;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoCriacao;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoJsonUsuario;
import school.sptech.loginormyou2up.dto.treino.TreinoDtoResposta;
import school.sptech.loginormyou2up.dto.usuario.UsuarioDtoRetornoDetalhes;

import java.util.ArrayList;
import java.util.List;

public class TreinoMapper {

    public static TreinoDtoJsonUsuario convertToTreinoDtoJsonUsuario(Treino treino) {
        TreinoDtoJsonUsuario dto = new TreinoDtoJsonUsuario();

        dto.setId(treino.getIdTreino());
        dto.setPeriodo(treino.getPeriodo());
        dto.setDataHora(treino.getUsuarios().get(0).getInicioTreino());

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

        treinoDtoResposta.setId(treino.getIdTreino());
        treinoDtoResposta.setPeriodo(treino.getPeriodo());
        treinoDtoResposta.setInicioTreino(treino.getUsuarios().get(0).getInicioTreino());
        treinoDtoResposta.setRealizado(treino.isRealizado());

        List<TreinoHasUsuario> lista = treino.getUsuarios();
        List<UsuarioDtoRetornoDetalhes> listaUsuarios = new ArrayList<>();

        for (TreinoHasUsuario tu : lista) {
            listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(tu.getUsuario()));
        }

        treinoDtoResposta.setUsuarios(listaUsuarios);

        return treinoDtoResposta;
    }

    public static TreinoDtoResposta convertToTreinoDtoResposta(Treino treino, List<TreinoHasUsuario> treinoHasUsuarios) {
        TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

        treinoDtoResposta.setId(treino.getIdTreino());
        treinoDtoResposta.setPeriodo(treino.getPeriodo());
        treinoDtoResposta.setInicioTreino(treinoHasUsuarios.get(0).getInicioTreino());
        treinoDtoResposta.setRealizado(treino.isRealizado());

        List<UsuarioDtoRetornoDetalhes> listaUsuarios = new ArrayList<>();

        for (TreinoHasUsuario tu : treinoHasUsuarios) {
            listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(tu.getUsuario()));
        }

        treinoDtoResposta.setUsuarios(listaUsuarios);

        return treinoDtoResposta;
    }

    public static List<TreinoDtoResposta> convertToTreinoDtoResposta(List<Treino> treinos) {
        List<TreinoDtoResposta> listaRetorno = new ArrayList<>();

        for (int i = 0; i < treinos.size(); i++) {

            TreinoDtoResposta treinoDtoResposta = new TreinoDtoResposta();

            treinoDtoResposta.setId(treinos.get(i).getIdTreino());
            treinoDtoResposta.setPeriodo(treinos.get(i).getPeriodo());

            treinoDtoResposta.setInicioTreino(treinos.get(i).getUsuarios().get(0).getInicioTreino());
            treinoDtoResposta.setRealizado(treinos.get(i).isRealizado());

            List<UsuarioDtoRetornoDetalhes> listaUsuarios = new ArrayList<>();

            for (TreinoHasUsuario tu: treinos.get(i).getUsuarios()) {
                listaUsuarios.add(UsuarioMapper.convertToUsuarioDtoRetornoDetalhes(tu.getUsuario()));
            }

            treinoDtoResposta.setUsuarios(listaUsuarios);

            listaRetorno.add(treinoDtoResposta);
        }



        return listaRetorno;
    }

    public static LocalTreinoUsuario convertToLocalTreinoUsuario(LocalTreinoCriacaoDto localTreinoCriacaoDto) {
        LocalTreinoUsuario localTreinoUsuario = new LocalTreinoUsuario();

        localTreinoUsuario.setNome(localTreinoCriacaoDto.getNome());
        localTreinoUsuario.setNumero(localTreinoCriacaoDto.getNumero());
        localTreinoUsuario.setRua(localTreinoCriacaoDto.getRua());
        localTreinoUsuario.setBairro(localTreinoCriacaoDto.getBairro());
        localTreinoUsuario.setCidade(localTreinoCriacaoDto.getCidade());
        localTreinoUsuario.setUf(localTreinoCriacaoDto.getUf());
        localTreinoUsuario.setAcademia(localTreinoCriacaoDto.isAcademia());

        return localTreinoUsuario;
    }

}
