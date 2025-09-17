package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import br.com.senai.UrbanSwift.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);

    }
    public Usuario cadastrar(Usuario u) {
        return usuarioRepository.save(u);
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
        return usuarioRepository.save(uExistente);

    }
    public Usuario deletar(Integer id) {
        Usuario uExistente = buscarPorId(id);

        if (uExistente == null) {
            return null;
        }

        usuarioRepository.delete(uExistente);
        return uExistente;
    }

}
