package br.com.sptech.login02221036rafaella.controllers;

import br.com.sptech.login02221036rafaella.dao.UsuarioDao;
import br.com.sptech.login02221036rafaella.dto.UsuarioDto;
import br.com.sptech.login02221036rafaella.dto.UsuarioForm;
import br.com.sptech.login02221036rafaella.models.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private static UsuarioDao usuarioDao = new UsuarioDao();

    @GetMapping
    public List<UsuarioDto> consultaUsuarios() {
        return UsuarioDto.converter(usuarioDao.getUsuarioList());
    }

    //Endpoint que retorna uma lista de usuarios cujo nome comece com a letra fornecida na request
    @GetMapping("/{letra}")
    public List<UsuarioDto> retornaPorLetraNome(@PathVariable String letra) {
        return UsuarioDto.converter(usuarioDao.retornaPorLetraNome(letra));
    }

    @PostMapping
    public String cadastraUsuario(@RequestBody UsuarioForm usuarioForm) {
        usuarioDao.salvaUsuario(new Usuario(usuarioForm.getUsuario(), usuarioForm.getNome(), usuarioForm.getSenha()));
        return "OK";
    }

    @PostMapping("/autenticacao/{usuario}")
    public UsuarioDto cadastraUsuario(@PathVariable String usuario, @RequestParam String senha, @RequestBody UsuarioForm usuarioForm) {
        return new UsuarioDto(usuarioDao.logar(usuario, senha));
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String deletaUsuario(@PathVariable String usuario) {
        return usuarioDao.deslogar(usuario);
    }
}
