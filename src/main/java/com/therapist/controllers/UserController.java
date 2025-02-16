package com.therapist.controllers;

import com.therapist.dto.LoginRequestDTO;
import com.therapist.dto.UserRequestDTO;
import com.therapist.dto.UserResponseDTO;
import com.therapist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<UserResponseDTO> createUserRegistration(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO  createUser = userService.storeUserDetails(userRequestDTO);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
       String login =  userService.checkUserCredential(loginRequestDTO);
        return new ResponseEntity<>(login,HttpStatus.CREATED);
    }
}
