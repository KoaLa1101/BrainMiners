package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itlab.models.Templates;
import ru.itlab.models.User;

import java.util.List;


@Repository
public interface TemplatesRepository extends JpaRepository<Templates, Integer> {
    List<Templates> findAllByUserList (User user);
}
