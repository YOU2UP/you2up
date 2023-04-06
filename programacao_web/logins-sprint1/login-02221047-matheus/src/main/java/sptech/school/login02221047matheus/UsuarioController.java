package sptech.school.login02221047matheus;

import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping()
    public UsuarioDTO cadastrar(@RequestBody Usuario novoUsuario) {
        this.usuarios.add(novoUsuario);
        UsuarioDTO userDto = new UsuarioDTO(novoUsuario);
        return userDto;
    }

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public UsuarioDTO autenticar(@PathVariable String usuario, @PathVariable String senha) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario) &&
                    usuarios.get(i).getSenha().equals(senha)) {
                Usuario userAutenticado = new Usuario(usuario, senha,
                                                        usuarios.get(i).getNome(),true);
                this.usuarios.set(i, userAutenticado);
                return new UsuarioDTO(userAutenticado);
            }
        }
        return null;
    }

    @GetMapping()
    public List<UsuarioDTO> listar() {
        List<UsuarioDTO> listaUser = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioDTO userDto = new UsuarioDTO(usuarios.get(i));
            listaUser.add(userDto);
        }
        return listaUser;
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String desautenticar(@PathVariable String usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuario)) {
                if (usuarios.get(i).isAutenticado()) {
                    Usuario userAutenticado = new Usuario(usuario, usuarios.get(i).getSenha(),
                                                            usuarios.get(i).getNome(),false);
                    this.usuarios.set(i, userAutenticado);
                    return String.format("Logoff do usuário %s concluído", usuarios.get(i).getNome());
                } else {
                    return String.format("Usuário %s não está autenticado", usuarios.get(i).getNome());
                }
            }
        }
        return String.format("Usuário %s não encontrado", usuario);
    }

    @GetMapping("/listar/{nome}")
    public UsuarioDTO listarNome(@PathVariable String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNome().equals(nome)) {
                UsuarioDTO userDto = new UsuarioDTO(usuarios.get(i));
                return userDto;
            }
        }
        return null;
    }

}


