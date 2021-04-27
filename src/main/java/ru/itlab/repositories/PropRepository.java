package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itlab.models.Properties;

public interface PropRepository extends JpaRepository<Properties, Integer> {
}
