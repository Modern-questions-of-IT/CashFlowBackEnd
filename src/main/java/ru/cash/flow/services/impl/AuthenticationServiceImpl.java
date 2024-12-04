package ru.cash.flow.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.JwtAuthenticationResponse;
import ru.cash.flow.dto.SignInRequest;
import ru.cash.flow.dto.SignUpRequest;
import ru.cash.flow.entities.User;
import ru.cash.flow.enums.ERole;
import ru.cash.flow.exceptions.InvalidPasswordException;
import ru.cash.flow.repositories.UserRepository;
import ru.cash.flow.services.AuthenticationService;
import ru.cash.flow.services.JwtService;
import ru.cash.flow.services.UserService;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        Optional<User> userOp = userRepository.findByEmail(request.getEmail());
        User user;
        if (userOp.isPresent()) {
            user = userOp.get();
        } else {
            user = User.builder()
                    .email(request.getEmail())
                    .passwordHash(passwordEncoder.encode(request
                            .getPassword())).role(ERole.ROLE_STANDARD)
                    .name(request.getName())
                    .createdAt(Instant.now())
                    .build();

            userService.create(user);
        }

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }


    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not fount by username: " + request.getEmail()));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            UserDetails userDetails = userService
                    .userDetailsService()
                    .loadUserByUsername(request.getEmail());

            var jwt = jwtService.generateToken(userDetails);
            return new JwtAuthenticationResponse(jwt);
        } else {
            throw new InvalidPasswordException(request.getEmail());
        }
    }

}