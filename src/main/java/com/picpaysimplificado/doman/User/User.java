package com.picpaysimplificado.doman.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity(name = "users") // representar uma entidade na tabela do banco de dados
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id") // vai ser a chave primeira dessa tabela
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) // deve ser unico
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance; // saldo do usuario
    @Enumerated(EnumType.STRING) // esse campo representa um dos valores do enum
    private UserType userType;
}
