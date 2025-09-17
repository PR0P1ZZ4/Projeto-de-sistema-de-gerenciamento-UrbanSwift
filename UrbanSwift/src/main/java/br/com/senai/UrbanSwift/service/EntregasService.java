package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.Entregas;
import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.repository.EntregasRepository;
import br.com.senai.UrbanSwift.repository.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntregasService {
    private final EntregasRepository entregasRepository;

    public EntregasService(EntregasRepository entregasRepository) {
        this.entregasRepository = entregasRepository;
    }

    public List<Entregas> findAll() {
        return entregasRepository.findAll();
    }
    public Entregas buscarPorId(Integer id) {
        return entregasRepository.findById(id).orElse(null);

    }
    public Entregas cadastrar(Entregas et) {
        return entregasRepository.save(et);
    }
    public Entregas atualizar(Integer id, Entregas etNovo) {
        Entregas etExistente = buscarPorId(id);

        if (etExistente != null) {
            return null;
        }

        etExistente.setUsuario(etNovo.getUsuario());
        etExistente.setDataPedido(etNovo.getDataPedido());
        etExistente.setEndereco(etNovo.getEndereco());
        etExistente.setStatus(etNovo.getStatus());
        return entregasRepository.save(etExistente);
    }
    public Entregas deletar(Integer id) {
        Entregas etExistente = buscarPorId(id);

        if (etExistente == null) {
            return null;
        }

        entregasRepository.delete(etExistente);
        return etExistente;
    }
}
