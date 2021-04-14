package ru.itlab.services;

import ru.itlab.dto.UserDto;
import ru.itlab.models.User;

import java.util.List;

public interface UserRestService {
    List<UserDto> getAllUsers();

    UserDto addUser(UserDto user);

    UserDto updUser(int userId, UserDto user);

    void deleteUser(int userId);

}
