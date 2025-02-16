package com.therapist.dto;

import com.therapist.configuration.Roles;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class UserRequestDTO {

    private String username;
    private String password;

    @NotNull(message = "Roles cannot be null")
    private Roles roles;

    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
