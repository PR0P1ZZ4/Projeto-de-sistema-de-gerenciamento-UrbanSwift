package br.com.senai.UrbanSwift.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//lombok
@Getter
@Setter
// obrigatorio para o jpa funcionar
@NoArgsConstructor
@AllArgsConstructor
//JPA
// entity informa que a classe é uma tabela
@Entity
// table permite que eu possa configurar a tabela
@Table(name = "tipo_usuario")
public class TipoUsuario {

    // reconhece que é chave primaria
    @Id
    // gerador de chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // colun configura a coluna
    // nullable se pode ser nulo ou nao
    @Column(name = "tipo_usuario_id", nullable = false)
    private Integer tipoUsuario;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

}