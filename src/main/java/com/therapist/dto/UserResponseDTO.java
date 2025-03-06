package com.therapist.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserResponseDTO {

    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String roles;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
