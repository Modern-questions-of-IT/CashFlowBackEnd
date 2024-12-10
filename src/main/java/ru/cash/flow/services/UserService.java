package ru.cash.flow.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.cash.flow.entities.User;

public interface UserService {
    User save(User user);

    User get(Long user);

    User create(User user);

    User getByUsername(String username);

    UserDetailsService userDetailsService();

    User getCurrentUser();

}
