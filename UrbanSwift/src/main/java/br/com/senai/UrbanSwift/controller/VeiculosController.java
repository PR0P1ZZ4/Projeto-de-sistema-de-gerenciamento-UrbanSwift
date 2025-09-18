package br.com.senai.UrbanSwift.controller;

import br.com.senai.UrbanSwift.model.Entregas;
import br.com.senai.UrbanSwift.model.Veiculos;
import br.com.senai.UrbanSwift.service.VeiculosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculosController {
    private final VeiculosService veiculosService;

    public VeiculosController(VeiculosService veiculosService) {
        this.veiculosService = veiculosService;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os veiculos", description = "Retorna uma lista com todos os veiculos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Veiculos>> listarEntregas() {
        List<Veiculos> veiculos = veiculosService.findAll();
        return ResponseEntity.ok(veiculos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um veiculo por ID", description = "Retorna um veiculo específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarVeiculosPorId(@PathVariable Integer id) {
        Veiculos veiculos = veiculosService.buscarPorId(id);

        if (veiculos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veiculo não encontrado!");
        }

        return ResponseEntity.ok(veiculos);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo veiculo", description = "Adiciona um novo veiculo ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veiculo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Veiculos> inserirVeiculos(@RequestBody Veiculos veiculos) {
        Veiculos novoVeiculos = veiculosService.cadastrar(veiculos);

        if (novoVeiculos == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 sem corpo se a entrada for inválida
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculos);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um veiculo existente", description = "Altera os dados de um veiculo com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarVeiculos(@PathVariable Integer id, @RequestBody Veiculos veiculos) {
        Veiculos veiculosAtualizado = veiculosService.atualizar(id, veiculos);

        if (veiculosAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Veiculo não encontrado!");
        }

        return ResponseEntity.ok(veiculosAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um veiculo", description = "Remove um veiculo do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veiculo não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarVeiculos(@PathVariable Integer id) {
        Veiculos deletado = veiculosService.deletar(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o veiculo não foi encontrado.");
        }

        return ResponseEntity.ok("Veiculo excluído com sucesso!");
    }
}
