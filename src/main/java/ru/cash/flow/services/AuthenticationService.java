package ru.cash.flow.services;


import ru.cash.flow.dto.*;

public interface AuthenticationService {

    JwtAuthenticationResponse signIn(SignInRequest request);

    Long changePassword(ChangePasswordRequest changePasswordRequest);

    JwtAuthenticationResponse changeUserData(ChangeUsernameRequest changeUserDataRequest);

    ProfileDto getInfo();

}
