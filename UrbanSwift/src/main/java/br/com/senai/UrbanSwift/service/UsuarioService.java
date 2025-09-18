package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import br.com.senai.UrbanSwift.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    }

    }

    }
    public Usuario cadastrar(Usuario u) {
    }
    public Usuario atualizar(Integer id, Usuario uNovo) {
        Usuario uExistente = buscarPorId(id);

        if (uExistente != null) {
            return null;
        }

        uExistente.setNomeCompleto(uNovo.getNomeCompleto());
        uExistente.setEmail(uNovo.getEmail());
        uExistente.setTipoUsuario(uNovo.getTipoUsuario());
        uExistente.setSenha(uNovo.getSenha());

    }
    public Usuario deletar(Integer id) {
        Usuario uExistente = buscarPorId(id);

        if (uExistente == null) {
            return null;
        }

        return uExistente;
    }

}
