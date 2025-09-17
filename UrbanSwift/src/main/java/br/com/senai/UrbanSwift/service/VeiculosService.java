package br.com.senai.UrbanSwift.service;

import br.com.senai.UrbanSwift.model.Entregas;
import br.com.senai.UrbanSwift.model.Veiculos;
import br.com.senai.UrbanSwift.repository.EntregasRepository;
import br.com.senai.UrbanSwift.repository.VeiculosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculosService {

    private final VeiculosRepository veiculosRepository;

    public VeiculosService(VeiculosRepository veiculosRepository) {
        this.veiculosRepository = veiculosRepository;
    }

    public List<Veiculos> findAll() {
        return veiculosRepository.findAll();
    }
    public Veiculos buscarPorId(Integer id) {
        return veiculosRepository.findById(id).orElse(null);

    }
    public Veiculos cadastrar(Veiculos vc) {
        return veiculosRepository.save(vc);
    }
    public Veiculos atualizar(Integer id, Veiculos vcNovo) {
        Veiculos vcExistente = buscarPorId(id);

        if (vcExistente != null) {
            return null;
        }

        vcExistente.setUsuario(vcNovo.getUsuario());
        vcExistente.setTipo(vcNovo.getTipo());
        vcExistente.setModelo(vcNovo.getModelo());
        vcExistente.setPlaca(vcNovo.getPlaca());
        return veiculosRepository.save(vcExistente);
    }
    public Veiculos deletar(Integer id) {
        Veiculos vcExistente = buscarPorId(id);

        if (vcExistente == null) {
            return null;
        }

        veiculosRepository.delete(vcExistente);
        return vcExistente;
    }
}
