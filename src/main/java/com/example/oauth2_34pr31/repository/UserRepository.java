package com.example.oauth2_34pr31.repository;

import com.example.oauth2_34pr31.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLoginAndProvider(String login, String provider);

    boolean existsByLoginAndProvider(String login, String provider);
}
