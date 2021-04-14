package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.dto.UserDto;
import ru.itlab.models.User;
import ru.itlab.repositories.UserRepository;

import java.util.List;

@Service
public class UserRestServiceImpl implements UserRestService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(userRepository.findAll());
    }

    @Override
    public UserDto addUser(UserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
        userRepository.save(newUser);
        return UserDto.from(newUser);

    }

    @Override
    public UserDto updUser(int userId, UserDto user) {
        User userForUpd = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        userForUpd.setFirstName(user.getFirstName());
        userForUpd.setLastName(user.getLastName());
        userForUpd.setRole(user.getRole());
        return UserDto.from(userForUpd);
    }

    @Override
    public void deleteUser(int userId) {
        User userToDel = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(userToDel);
    }
}
