package ru.itlab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itlab.models.Properties;
import ru.itlab.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private User.Role role;
    private Properties properties;

    public static UserDto from (User user){
        UserDto result = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        if(user.getPropertiesById() != null)
            result.setProperties(user.getPropertiesById());

        return result;
    }
    public static List<UserDto> from (List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
