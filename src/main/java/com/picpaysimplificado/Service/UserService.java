package com.picpaysimplificado.Service;

import com.picpaysimplificado.doman.User.User;
import com.picpaysimplificado.doman.User.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service // indica pro spring que é uma classe de serviço
public class UserService {

    @Autowired // o spring tem que fazer a injeção desse repository
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo logista não está autorizado para realizar uma transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Usuario não tem saldo suficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        // se não encontrar o usuario ele lança uma exceção com o orElseThrow
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public void saveUser(User user){
        // persistir as alterações no usuario
        this.repository.save(user);
    }
}

