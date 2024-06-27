package com.picpaysimplificado.doman.transaction;

import com.picpaysimplificado.doman.User.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity(name = "transactions")
@Table(name = "transactions")
@Data
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount; // valor da transação
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
}
