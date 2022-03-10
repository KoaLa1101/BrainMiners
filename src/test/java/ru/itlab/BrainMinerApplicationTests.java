package ru.itlab;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itlab.models.Message;
import ru.itlab.models.Properties;
import ru.itlab.services.MesService;
import ru.itlab.services.PropService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase(beanName = "dataSource")
@DisplayName("BrainMiners is working when")
public class BrainMinerApplicationTests {
    @Autowired
    MesService mesService;

    @Autowired
    PropService propService;

    @Nested
    @DisplayName("MesService is working when")
    class ForMesService {
        private Message message = Message.builder()
                .mes("ASDJKASDJASKDJAS")
                .id(1)
                .build();

        @Test
        public void on_valid_data_for_save_expect_true() {
            Assertions.assertTrue(mesService.saveMes(message));
        }

        @Test
        public void on_valid_data_for_delete_expect_true() {
            Assertions.assertTrue(mesService.removeMes(message));
        }

        @Test
        public void on_valid_value_get_message_expect_valid_data() {
            mesService.saveMes(message);
            Assertions.assertEquals(message, mesService.findById(message.getId()));
        }
    }

    @Nested
    @DisplayName("PropService is working when")
    class ForPropService {
        Properties properties = Properties.builder()
                .busyness("Part of day (4 hour)")
                .education("Higher education")
                .experience("Haven't an exp")
                .id(1)
                .levelOfEnglish("B1-C1")
                .salaryWork("more than 50001")
                .sphereOfWork("distant worker")
                .build();

        @Test
        public void on_valid_data_for_save_expect_true() {
            Assertions.assertTrue(propService.saveProp(properties));
        }

        @Test
        public void on_valid_data_for_delete_expect_true(){
            Assertions.assertTrue(propService.deleteProp(properties));
        }

        @Test
        public void on_valid_data_for_find_by_user_expect_true(){
            propService.saveProp(properties);
            Assertions.assertEquals(properties, propService.propByUser(1).get());
        }
    }
}
