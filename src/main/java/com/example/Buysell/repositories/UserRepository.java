package com.example.Buysell.repositories;

import com.example.Buysell.cofigyrations.UserConfig;
import com.example.Buysell.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser,Long> {
    Optional<MyUser> findByName(String email);
}
