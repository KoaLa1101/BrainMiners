package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itlab.models.Properties;

import java.util.List;

@Repository
public interface PropRepository extends JpaRepository<Properties, Integer> {
}
