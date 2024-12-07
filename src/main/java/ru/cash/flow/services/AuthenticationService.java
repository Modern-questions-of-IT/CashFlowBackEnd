package ru.cash.flow.services;


import ru.cash.flow.dto.*;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);

    Long changePassword(ChangePasswordRequest changePasswordRequest);

    Long changeUserData(ChangeUsernameRequest changeUserDataRequest);

    ProfileDto getInfo();

}
