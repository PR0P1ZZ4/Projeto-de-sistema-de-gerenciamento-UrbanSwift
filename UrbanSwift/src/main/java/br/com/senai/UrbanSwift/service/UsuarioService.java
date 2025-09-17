package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import br.com.senai.UrbanSwift.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
    }

    public static List<Usuario> findAll() {
        return UsuarioRepository.findAll();
    }
    public static Usuario buscarPorId(Integer id) {
        return UsuarioRepository.findById(id).orElse(null);

    }
    public Usuario cadastrar(Usuario u) {
        return UsuarioRepository.save(u);
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
        return UsuarioRepository.save(uExistente);

    }
    public Usuario deletar(Integer id) {
        Usuario uExistente = buscarPorId(id);

        if (uExistente == null) {
            return null;
        }

        UsuarioRepository.delete(uExistente);
        return uExistente;
    }

}
