package ru.cash.flow.services;


import ru.cash.flow.dto.JwtAuthenticationResponse;
import ru.cash.flow.dto.SignInRequest;
import ru.cash.flow.dto.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);

}
