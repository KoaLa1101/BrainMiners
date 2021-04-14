package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itlab.models.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
