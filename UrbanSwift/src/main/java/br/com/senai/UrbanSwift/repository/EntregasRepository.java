package br.com.senai.UrbanSwift.repository;

import br.com.senai.UrbanSwift.model.Entregas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregasRepository extends JpaRepository<Entregas, Integer> {
}
