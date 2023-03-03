package br.com.sptech.login02221036rafaella.dao;

import br.com.sptech.login02221036rafaella.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDao {

    private static List<Usuario> usuarioList = new ArrayList<>();

    public void salvaUsuario(Usuario usuario) {
        usuarioList.add(usuario);
        System.out.println();
    }

    public List<Usuario> retornaPorLetraNome(String letra) {
        return usuarioList.stream().filter(usuario -> usuario.getNome().startsWith(letra)).collect(Collectors.toList());
    }

    public Usuario logar(String usuarioLogin, String senha) {
        for (Usuario usuario: usuarioList) {
            if(usuario.getUsuario().equals(usuarioLogin) && usuario.getSenha().equals(senha)) {
                usuario.setAutenticado(true);
                return usuario;
            }
        }
        return null;
    }

    public String deslogar(String usuarioLogoff) {
        for (Usuario usuario: usuarioList) {
            if(usuario.getUsuario().equals(usuarioLogoff)) {
                if(usuario.getAutenticado() == true) {
                    usuario.setAutenticado(false);
                    return "Logoff do usuário " + usuario.getNome() + " concluído";
                } else if(usuario.getAutenticado() == false) {
                    return "Usuário " + usuario.getNome() + " não está autenticado";
                }
            }
        }
        return "Usuário " + usuarioLogoff + " não encontrado";
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }
}
