package com.example.Buysell.services;

import com.example.Buysell.models.Enums.Role;
import com.example.Buysell.models.User;
import com.example.Buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Seving new User with email: {}", email);
        userRepository.save(user);
        return true;

    }

    public List<User> list() {
        return userRepository.findAll();
    }


    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {

            if(user.isActive()){
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            }else {
                user.setActive(true);
                log.info("Unbundle user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            }


        assert user != null;
        userRepository.save(user);
        }




    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void deleteUser(long id){

        log.info("Удаляем пользователя {}",userRepository.findById(id));
        userRepository.deleteById(id);
        if(userRepository.findById(id).isEmpty()){
            log.info("Пользователь  удален");
        }else {
            log.info("Не получилось удалить пользователя {}",userRepository.findById(id));
        }

    }
}
