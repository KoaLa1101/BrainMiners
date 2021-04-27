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

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update usr " +
            "set first_name =:param1, last_name =:param2, password =:param3, password_confirm =:param4," +
            " username =:param5 where usr.id =:param6")
    int updateUsr(@Param("param1") String firstName,@Param("param2") String lastName,@Param("param3") String password,
                  @Param("param4") String passwordConfirm,@Param("param5") String username, @Param("param6") int userid);

    @Query(nativeQuery = true, value = "select * from usr where usr.id =:param1")
    User showUser(@Param("param1") int userId);
}
