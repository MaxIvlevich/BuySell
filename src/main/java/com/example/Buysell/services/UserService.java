package com.example.Buysell.services;

import com.example.Buysell.models.Enums.Role;
import com.example.Buysell.cofigyrations.UserConfig;
import com.example.Buysell.models.MyUser;
import com.example.Buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserConfig user){
        String email = user.getUsername();
        if(userRepository.findByName(email).isPresent()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        log.info("Seving new User with email: {}",email);

        return true;

    }


}
