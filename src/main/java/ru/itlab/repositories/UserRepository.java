package ru.itlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itlab.models.Message;
import ru.itlab.models.Properties;
import ru.itlab.models.Templates;
import ru.itlab.models.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAllByRole(User.Role role);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update usr set properties =:param1 where usr.id =:param2 ")
    int updateProps(@Param("param1") int propertiesId, @Param("param2") int usrId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update usr " +
            "set first_name =:param1, last_name =:param2, password =:param3, password_confirm =:param4," +
            " username =:param5 where usr.id =:param6")
    int updateUsr(@Param("param1") String firstName, @Param("param2") String lastName, @Param("param3") String password,
                  @Param("param4") String passwordConfirm, @Param("param5") String username, @Param("param6") int userid);

    @Query(nativeQuery = true, value = "select * from usr where usr.id =:param1")
    User showUser(@Param("param1") int userId);

    @Query(nativeQuery = true, value = "select * from usr inner join properties on usr.id = properties.id where (properties.id=:id or :id is null or :id='_') and (properties.education=:education or :education is null or :education='_') and (properties.busyness=:busyness or :busyness is null or :busyness='_') and (properties.experience=:experience or :experience is null or :experience='_') and (properties.level_of_english=:levelOfEnglish or :levelOfEnglish is null or :levelOfEnglish='_')  and (properties.salary_work=:salaryWork or :salaryWork is null or :salaryWork='_') and (properties.sphere_of_work=:sphereOfWork or :sphereOfWork is null or :sphereOfWork='_')")
    List<User> allUserByProps(@Param("id") int id, @Param("education") String education, @Param("busyness") String busyness, @Param("experience") String experience, @Param("levelOfEnglish") String levelOfEnglish, @Param("salaryWork") String salaryWork, @Param("sphereOfWork") String sphereOfWork);

    //@Query(nativeQuery = true, value = "select user_list_id, count (templates_id) from templates_user_list group by user_list_id order by count(templates_id)")

}
