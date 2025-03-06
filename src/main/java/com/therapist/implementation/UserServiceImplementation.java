package com.therapist.implementation;

import com.therapist.Exception.HandleBadRequestError;
import com.therapist.dto.LoginRequestDTO;
import com.therapist.dto.LoginResponseDTO;
import com.therapist.dto.UserRequestDTO;
import com.therapist.dto.UserResponseDTO;
import com.therapist.models.UserEntity;
import com.therapist.repositories.UserRepository;
import com.therapist.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserResponseDTO storeUserDetails(UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setRoles(String.valueOf(userRequestDTO.getRoles()));
        userRepository.save(userEntity);
        return modelMapper.map(userEntity,UserResponseDTO.class);
    }

    @Override
    public LoginResponseDTO checkUserCredential(LoginRequestDTO loginRequestDTO) {
       UserEntity userEntity = userRepository.findByUsernameAndPassword(loginRequestDTO.getUsername(),loginRequestDTO.getPassword());
       if(userEntity.getUsername().equals(loginRequestDTO.getUsername()) &&
               userEntity.getPassword().equals(loginRequestDTO.getPassword())){
            return new LoginResponseDTO("","");
       }else{
        throw new HandleBadRequestError("Invalid Credentials");
       }
    }
}
