package ru.itlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itlab.models.Message;
import ru.itlab.models.Properties;
import ru.itlab.models.User;
import ru.itlab.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return user;
    }

    public User findUserById(int userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


    public boolean deleteUser(int userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User.Role getRole(User user) {
        return user.getRole();
    }

    public int updateUser(User user) {
        return userRepository.updateUsr(user.getFirstName(), user.getLastName(), user.getPassword(), user.getPasswordConfirm(), user.getUsername(), user.getId());
    }

    public User showUser(int userId) {
        return userRepository.showUser(userId);
    }

    public List<User> allUserByRole(User.Role role) {
        return userRepository.findAllByRole(role);
    }

    public List<User> findAllByProperties(Properties properties) {
        return userRepository.allUserByProps(properties.getId(), properties.getEducation(), properties.getBusyness(), properties.getExperience(), properties.getLevelOfEnglish(), properties.getSalaryWork(), properties.getSphereOfWork());
    }

    public List<Message> allMes(User user) {
        return user.getMessageList();
    }

    public int updateProps(int properties_id, int usr_id) {
        return userRepository.updateProps(properties_id, usr_id);
    }
}
