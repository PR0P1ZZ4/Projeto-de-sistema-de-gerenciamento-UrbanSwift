package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.Enderecos;
import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.repository.EnderecosRepository;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnderecosService {
    private final EnderecosRepository enderecosRepository;

    public EnderecosService(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }

    public List<Enderecos> findAll() {
        return enderecosRepository.findAll();
    }
    public Enderecos buscarPorId(Integer id) {
        return enderecosRepository.findById(id).orElse(null);

    }
    public Enderecos cadastrar(Enderecos e) {
        return enderecosRepository.save(e);
    }
    public Enderecos atualizar(Integer id, Enderecos e) {
        Enderecos eExistente = buscarPorId(id);

        if (eExistente != null) {
            return null;
        }

        e.setUsuario(e.getUsuario());
        return enderecosRepository.save(eExistente);
    }
    public Enderecos deletar(Integer id) {
        Enderecos eExistente = buscarPorId(id);

        if (eExistente == null) {
            return null;
        }

        enderecosRepository.delete(eExistente);
        return eExistente;
    }
}
