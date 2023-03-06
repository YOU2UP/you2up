package school.sptech.login02221025vinicius.controllers;


import org.springframework.web.bind.annotation.*;
import school.sptech.login02221025vinicius.classes.Usuario;
import school.sptech.login02221025vinicius.dtos.UsuarioDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public UsuarioDto cadastrarUsuarios(@RequestBody Usuario usuario){
        usuarios.add(usuario);
        return new UsuarioDto(usuario);
    }


    @PostMapping("/autenticacao/{usuario}/{senha}")
    public UsuarioDto autenticarUsuario(
            @PathVariable String usuario,
            @PathVariable String senha
    ){
        for (Usuario u: usuarios) {
            if(u.getSenha().equals(senha) && u.getUsuario().equals(usuario)){
                u.setAutenticado(true);
                return new UsuarioDto(u);
            }
        }

        return null;
    }

    @GetMapping
    public List<UsuarioDto> mostrarUsuarios(){
        List<UsuarioDto> listaRetorno = new ArrayList<>();
        for (Usuario u: usuarios) {
            UsuarioDto user = new UsuarioDto(u);
            listaRetorno.add(user);
        }

        return listaRetorno;
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String logoff(@PathVariable String usuario){

        for (Usuario u: usuarios) {
            if(u.getUsuario().equals(usuario) && u.isAutenticado()){
                u.setAutenticado(false);
                return "Logoff do usuário " + u.getUsuario() + " concluído";
            }
            else if(u.getUsuario().equals(usuario) && !u.isAutenticado()){
                return "O usuário " + u.getNome() + " não está autenticado";
            }
        }

        return "Usuário " + usuario + " não encontrado";
    }


    @GetMapping("/{indice}")
    public UsuarioDto getByIndice(@PathVariable int indice){
        return new UsuarioDto(usuarios.get(indice));
    }


//    verificar se há duplicidade de nome de usuário,
//    se houver retorna os jsons dos usuários duplicados
//    se não houver não retorna nada


    @GetMapping("/duplicados/{usuario}")
    public List<UsuarioDto> verificarUsuarioDuplicado(@PathVariable String usuario){
        List<UsuarioDto> listaRetorno = new ArrayList<>();
        int numOcorrencias = 0;

        for (Usuario u: usuarios) {
            if(u.getUsuario().equals(usuario)){
                numOcorrencias ++;
                listaRetorno.add(new UsuarioDto(u));
            }
        }

        if(numOcorrencias > 1){
            return listaRetorno;
        }

        return null;
    }



}
