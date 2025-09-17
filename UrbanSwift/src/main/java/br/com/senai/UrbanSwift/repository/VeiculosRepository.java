package br.com.senai.UrbanSwift.repository;

import br.com.senai.UrbanSwift.model.Entregas;
import br.com.senai.UrbanSwift.model.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Integer> {
}
