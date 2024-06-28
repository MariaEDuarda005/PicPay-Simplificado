package com.picpaysimplificado.repositories;

import com.picpaysimplificado.doman.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document); // definindo um metodo que vai buscar os usuarios pelo documento

    Optional<User> findUserById(Long id);
}
