package br.com.senai.UrbanSwift.repository;

import br.com.senai.UrbanSwift.model.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Integer> {
}
