package br.com.senai.UrbanSwift.controller;

import br.com.senai.UrbanSwift.model.Usuario;
import br.com.senai.UrbanSwift.repository.UsuarioRepository;
import br.com.senai.UrbanSwift.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/usuarios")
    @Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuarios")
    public class UsuarioController {
        private UsuarioService usuarioService;
        public UsuarioController(UsuarioService service) {
            this.usuarioService = service;
        }

        // Listar todos
        @GetMapping
        @Operation(summary = "Lista todos os usuarios", description = "Retorna uma lista com todos os usuarios cadastrados")
        @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
        public ResponseEntity<List<Usuario>> listarUsuario() {
            List<Usuario> usuario = usuarioService.listarTodos();
            return ResponseEntity.ok().body(usuario);
        }

        // Buscar por ID
        @GetMapping("/{id}")
        @Operation(summary = "Busca um usuário por ID", description = "Retorna um usuário específico com base no seu ID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID informado")
        })
        public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
            Usuario usuario = usuarioService.buscarPorId(id);

            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
            }
            return ResponseEntity.ok(usuario);
        }

        // Inserir Novo
        @PostMapping
        @Operation(summary = "Cadastra um novo usuário", description = "Adiciona um novo usuário ao banco de dados.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para o cadastro")
        })
        public ResponseEntity<Usuario> inserirUsuario (@RequestBody Usuario usuario) {
            Usuario usuarioNovo = usuarioService.cadastrarUsuario(usuario);

            if (usuarioNovo == null) {
                return ResponseEntity.badRequest().build(); // Retorna 400 sem corpo se a entrada for invalida
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
        }

        // Atualizar
        @PutMapping("/{id}")
        @Operation(summary = "Atualiza um usuário existente", description = "Altera os dados de usuário com base no seu ID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID informado")
        })
        public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);

            if (usuarioAtualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
            }
            return ResponseEntity.ok(usuarioAtualizado);
        }

        // Deletar
        @DeleteMapping("/{id}")
        @Operation(summary = "Exclui um usuário", description = "Remove um usuário do banco de dados com base no seu ID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID informado")
        })
        public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
            Usuario deletado = usuarioService.deletar(id);

            if (deletado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel excluir, pois o usuario nao foi encontrado");
            }
            return ResponseEntity.ok("Usuario excluido com sucesso");
        }
    }
