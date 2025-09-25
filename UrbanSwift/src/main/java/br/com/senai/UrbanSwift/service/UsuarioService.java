package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import br.com.senai.UrbanSwift.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private UsuarioRepository usuarioRepository;
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository repo) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = repo;
    }

    // Listar
    public List<Usuario> listarTodos() {
        return this.usuarioRepository.findAll();
    }

    // Buscar por ID
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Cadastrar
    public Usuario cadastrarUsuario(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);


        return usuarioRepository.save(usuario);
    }



    // Atualizar
    public Usuario atualizarUsuario(Integer id, Usuario usuarioNovo) {
        Usuario usuarioExistente = buscarPorId(id);
        if (usuarioExistente == null) {
            return null;
        }
        usuarioExistente.setNomeCompleto(usuarioNovo.getNomeCompleto());
        usuarioExistente.setEmail(usuarioNovo.getEmail());
        usuarioExistente.setSenha(usuarioNovo.getSenha());
        return usuarioRepository.save(usuarioExistente);
    }

    // Deletar
    public Usuario deletar(Integer id) {
        Usuario usuarioExistente = buscarPorId(id);
        if (usuarioExistente == null) {
            return null;
        }
        usuarioRepository.delete(usuarioExistente);
        return usuarioExistente;
    }

    // Esse método recebe o ID do usuario a ser nydadi e um objeto 'uNovo'
    // com as informacoes novas que vieram da sua API
    public Usuario atualizar(Integer id, Usuario uNovo) {
        // 1. Buscamos a versao ATUAL do usuario que esta no banco de dados.
        // Não podemos trabalhar em cima dos dados que vieram da API diretamete.
        // Precisamos primeiro pegar o "original" do banco
        Usuario uExistente = buscarPorId(id);

        // Atualizando ...
        uExistente.setNomeCompleto(uNovo.getNomeCompleto());
        uExistente.setEmail(uNovo.getEmail());
        return usuarioRepository.save(uNovo);
    }
}
