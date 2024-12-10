package ru.cash.flow.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.cash.flow.entities.User;
import ru.cash.flow.enums.ERole;
import ru.cash.flow.exceptions.UserUsernameAlreadyExistsException;
import ru.cash.flow.repositories.UserRepository;
import ru.cash.flow.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;


    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User get(Long user) {
        return repository.findById(user).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + user));
    }


    @Override
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UserUsernameAlreadyExistsException(user.getEmail());
        }
        return save(user);
    }


    @Override
    public User getByUsername(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + email));
    }


    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}