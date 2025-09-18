package br.com.senai.UrbanSwift.controller;

import br.com.senai.UrbanSwift.model.Enderecos;
import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.service.EnderecosService;
import br.com.senai.UrbanSwift.service.TipoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecosController {

    private final EnderecosService enderecosService;

    public EnderecosController(EnderecosService enderecosService) {
        this.enderecosService = enderecosService;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os enderecos", description = "Retorna uma lista com todos os enderecos cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Enderecos>> listarEnderecos() {
        List<Enderecos> enderecos = enderecosService.findAll();
        return ResponseEntity.ok(enderecos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um enderecos por ID", description = "Retorna um enderecos específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enderecos encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Enderecos não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarEnderecosPorId(@PathVariable Integer id) {
        Enderecos enderecos = enderecosService.buscarPorId(id);

        if (enderecos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado!");
        }

        return ResponseEntity.ok(enderecos);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo endereço", description = "Adiciona um novo endereço ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Enderecos> inserirEnderecos(@RequestBody Enderecos enderecos) {
        Enderecos novoEnderecos = enderecosService.cadastrar(enderecos);

        if (novoEnderecos == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 sem corpo se a entrada for inválida
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoEnderecos);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço existente", description = "Altera os dados de um endereço com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarEnderecos(@PathVariable Integer id, @RequestBody Enderecos enderecos) {
        Enderecos enderecosAtualizado = enderecosService.atualizar(id, enderecos);

        if (enderecosAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado!");
        }

        return ResponseEntity.ok(enderecosAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um endereço", description = "Remove um endereço do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarEnderecos(@PathVariable Integer id) {
        Enderecos deletado = enderecosService.deletar(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o endereço não foi encontrado.");
        }

        return ResponseEntity.ok("Endereço excluído com sucesso!");
    }
}
