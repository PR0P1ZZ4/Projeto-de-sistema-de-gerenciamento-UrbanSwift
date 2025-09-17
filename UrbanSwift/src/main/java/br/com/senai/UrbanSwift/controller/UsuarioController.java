package br.com.senai.UrbanSwift.controller;

import br.com.senai.UrbanSwift.model.TipoUsuario;
import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService service) {
        this.usuarioService = service;
    }

    // Listar Todos
    @GetMapping
    @Operation(summary = "Lista todos os usuário", description = "Retorna uma lista com todos os usuário cadastrados.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> usuario = UsuarioService.findAll();
        return ResponseEntity.ok(usuario);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID", description = "Retorna um usuário específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario Usuario = UsuarioService.buscarPorId(id);

        if (Usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(Usuario);
    }

    // Inserir Novo
    @PostMapping
    @Operation(summary = "Cadastra um novo usuário", description = "Adiciona um novo tipo de usuário ao banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
    })
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);

        if (novoUsuario == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 sem corpo se a entrada for inválida
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente", description = "Altera os dados de um usuário com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuario);

        if (usuarioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(usuario);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um usuário", description = "Remove um tipo de usuário do banco de dados com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado para o ID informado")
    })
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
        Usuario deletado = usuarioService.deletar(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, pois o usuário não foi encontrado.");
        }

        return ResponseEntity.ok("usuário excluído com sucesso!");
    }
}
