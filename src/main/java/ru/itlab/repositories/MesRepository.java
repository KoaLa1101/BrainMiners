package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itlab.models.Message;

@Repository
public interface MesRepository extends JpaRepository<Message, Integer> {
}
