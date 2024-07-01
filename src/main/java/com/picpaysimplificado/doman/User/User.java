package com.picpaysimplificado.doman.User;

import com.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "users") // representar uma entidade na tabela do banco de dados
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
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


    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.email = data.email();
    }
}
