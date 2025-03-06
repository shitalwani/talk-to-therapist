package com.therapist.services;

import com.therapist.dto.LoginRequestDTO;
import com.therapist.dto.LoginResponseDTO;
import com.therapist.dto.UserRequestDTO;
import com.therapist.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO storeUserDetails(UserRequestDTO userRequestDTO);

    LoginResponseDTO checkUserCredential(LoginRequestDTO loginRequestDTO);
}
