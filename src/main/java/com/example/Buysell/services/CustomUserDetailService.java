package com.example.Buysell.services;

import com.example.Buysell.models.MyUser;
import com.example.Buysell.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Buysell.cofigyrations.UserConfig;
import java.util.Optional;

@Service

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByName(email);
        return user.map(UserConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
