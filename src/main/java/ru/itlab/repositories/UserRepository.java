package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itlab.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update usr set properties =:param1 where usr.id =:param2 ")
    int updateProps(@Param("param1") int propertiesId, @Param("param2") int usrId);

    //TODO: Make update user in profile
}
