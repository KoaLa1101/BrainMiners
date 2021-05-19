package ru.itlab.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itlab.models.User;
import ru.itlab.models.forms.HhForm;

@Component
@Slf4j
public class OauthConverter implements Converter<HhForm, User> {
    @Override
    public User convert(HhForm hhForm) {
        User user = new User();
        user.setFirstName(hhForm.getFirst_name());
        user.setLastName(hhForm.getLast_name());
        user.setUsername(hhForm.getFirst_name() + "_" + hhForm.getLast_name());
        user.setPassword(hhForm.getPassword());
        user.setPasswordConfirm(hhForm.getPassword());
        user.setRole(hhForm.getRole());
        return user;
    }
}
