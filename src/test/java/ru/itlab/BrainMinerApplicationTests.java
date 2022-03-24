package ru.itlab;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itlab.models.Message;
import ru.itlab.models.Properties;
import ru.itlab.models.Templates;
import ru.itlab.models.User;
import ru.itlab.services.MesService;
import ru.itlab.services.PropService;
import ru.itlab.services.TempService;
import ru.itlab.services.UserService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
@DisplayName("BrainMiners is working when")
public class BrainMinerApplicationTests {


    private static final Logger LOGGER = LogManager.getLogger(BrainMinerApplication.class);

    @Autowired
    private MesService mesService;
    @Autowired
    private PropService propService;
    @Autowired
    private UserService userService;
    @Autowired
    private TempService tempService;

    private User user = User.builder()
            .id(0)
            .username("koala1101")
            .firstName("Rinat")
            .lastName("Akhnethanov")
            .password("Qwert123")
            .passwordConfirm("Qwert123")
            .role(User.Role.EMPLOYEE)
            .build();

    private Message message = Message.builder()
            .mes("ASDJKASDJASKDAS")
            .id(0)
            .build();

    private Properties properties = Properties.builder()
            .busyness("Part of day (4 hour)")
            .education("Higher education")
            .experience("Haven't an exp")
            .id(0)
            .levelOfEnglish("B1-C1")
            .salaryWork("more than 50001")
            .sphereOfWork("distant worker")
            .build();

    private Templates templates = Templates.builder()
            .id(1)
            .mes("asdasdasdad")
            .build();

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("MesService is working when")
    class ForMesService {

        @Test
        @Order(1)
        public void on_valid_data_for_save_expect_true() {
            Assertions.assertTrue(mesService.saveMes(message));
        }

        @Test
        @Order(2)
        public void on_valid_data_for_delete_expect_true() {
            Assertions.assertTrue(mesService.removeMes(message));
        }

        @Test
        @Order(3)
        public void on_valid_value_get_message_expect_valid_data() {
            mesService.saveMes(message);

            Assertions.assertEquals(message, mesService.findById(message.getId()));
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("PropService is working when")
    class ForPropService {

        @Test
        @Order(1)
        public void on_valid_data_for_save_expect_true() {
            Assertions.assertTrue(propService.saveProp(properties));
        }

        @Test
        @Order(3)
        public void on_valid_data_for_delete_expect_true() {
            Assertions.assertTrue(propService.deleteProp(properties));
        }

        @Test
        @Order(2)
        public void on_valid_data_for_find_by_user_expect_true() {
            Assertions.assertEquals(properties, propService.propByUser(0).get());
        }
    }

    @Nested
    @DisplayName("UserService working when")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ForUserService {

        @Test
        @Order(1)
        public void on_valid_data_for_save_user_expect_success() {
            Assertions.assertTrue(userService.saveUser(user));
        }

        @Test
        @Order(2)
        public void on_valid_username_for_load_user_expect_true() {
            Assertions.assertEquals(user.getUsername(), userService.loadUserByUsername("koala1101").getUsername());
        }

        @Test
        @Order(3)
        public void on_valid_user_expect_same_role() {
            Assertions.assertEquals(user.getRole(), userService.getRole(user));
        }

        @Test
        @Order(4)
        public void on_valid_data_for_update_expect_0() {
            User newUser = user;
            newUser.setUsername("koala11011");
            Assertions.assertEquals(userService.updateUser(newUser), 0);
        }

/*        @Test
        @Order(5)
        public void on_valid_data_for_show_user_assert_success() {
            Assertions.assertEquals(user, userService.showUser(user.getId()));
        }*/
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("TempService working when")
    class ForTempService {
        @Test
        @Order(1)
        public void on_valid_data_for_save_templates_expect_true(){
            Assertions.assertTrue(tempService.saveTemp(templates));
        }

        @Test
        @Order(2)
        public void on_valid_data_for_find_all_expect_equals(){
            Assertions.assertEquals(templates.getId(), tempService.findAll().get(0).getId());
        }
    }

}
